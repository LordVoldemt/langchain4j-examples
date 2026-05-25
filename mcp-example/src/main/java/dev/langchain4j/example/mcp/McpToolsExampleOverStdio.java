package dev.langchain4j.example.mcp;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolProvider;

import java.io.File;
import java.util.List;

public class McpToolsExampleOverStdio {

    // We will let the AI read the contents of this file
    public static final String FILE_TO_BE_READ = "src/main/resources/file.txt";

    /**
     * This example uses the `server-filesystem` MCP server to showcase how
     * to allow an LLM to interact with the local filesystem.
     * <p>
     * Running this example requires npm to be installed on your machine,
     * because it spawns the `server-filesystem` as a subprocess via npm:
     * `npm exec @modelcontextprotocol/server-filesystem@0.6.2`.
     * <p>
     * Of course, feel free to swap out the server with any other MCP server.
     * <p>
     * The communication with the server is done directly via stdin/stdout.
     * <p>
     * IMPORTANT: when executing this, make sure that the working directory is
     * equal to the root directory of the project
     * (`langchain4j-examples/mcp-example`), otherwise the program won't be able to find
     * the proper file to read. If you're working from another directory,
     * adjust the path inside the StdioMcpTransport.Builder() usage in the main method.
     */
    public static void main(String[] args) throws Exception {

        // 模型负责理解用户意图和选择工具，MCP server 负责真正访问外部能力。
        ChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
//                .logRequests(true)
//                .logResponses(true)
                .build();

        // stdio 模式会启动一个子进程，并通过标准输入/输出交换 MCP JSON-RPC 消息。
        // allowed directory 是重要安全边界：filesystem server 只能访问这个目录。
        McpTransport transport = new StdioMcpTransport.Builder()
                .command(List.of("/usr/bin/npm", "exec",
                        "@modelcontextprotocol/server-filesystem@0.6.2",
                        // allowed directory for the server to interact with
                        new File("src/main/resources").getAbsolutePath()
                ))
                .logEvents(true)
                .build();

        McpClient mcpClient = new DefaultMcpClient.Builder()
                .transport(transport)
                .build();

        // McpToolProvider 会把 MCP server 暴露的工具转换成 LangChain4j 可调用的工具列表。
        ToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(List.of(mcpClient))
                .build();

        Bot bot = AiServices.builder(Bot.class)
                .chatModel(model)
                .toolProvider(toolProvider)
                .build();

        try {
            File file = new File(FILE_TO_BE_READ);
            String response = bot.chat("Read the contents of the file " + file.getAbsolutePath());
            System.out.println("RESPONSE: " + response);
        } finally {
            // stdio 子进程和连接资源需要显式关闭，避免示例结束后残留后台进程。
            mcpClient.close();
        }
    }
}
