package dev.langchain4j.example.mcp.github;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolProvider;

import java.util.List;

public class McpGithubToolsExample {

    /**
     * This example uses the GitHub MCP server to showcase how
     * to use an LLM to summarize the last commits of a public GitHub repo.
     * Being a public repository (the LangChain4j repository is used as an example), you don't need any
     * authentication to access the data.
     * <p>
     * Running this example requires Docker to be installed on your machine,
     * because it spawns the GitHub MCP Server as a subprocess via Docker:
     * `docker run -i mcp/git`.
     * <p>
     * You first need to build the Docker image of the GitHub MCP Server that is available at `mcp/git`.
     * See https://github.com/modelcontextprotocol/servers/tree/main/src/git to build the image.
     * <p>
     * The communication with the GitHub MCP server is done directly via stdin/stdout.
     */
    public static void main(String[] args) throws Exception {

        ChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .logRequests(true)
                .logResponses(true)
                .build();

        // GitHub MCP server 通过 Docker 子进程启动；GITHUB_PERSONAL_ACCESS_TOKEN 只从环境变量透传，
        // 示例不硬编码 token，避免把真实凭证写入代码仓库。
        McpTransport transport = new StdioMcpTransport.Builder()
                .command(List.of("/usr/local/bin/docker", "run", "-e", "GITHUB_PERSONAL_ACCESS_TOKEN", "-i", "mcp/git"))
                .logEvents(true)
                .build();

        McpClient mcpClient = new DefaultMcpClient.Builder()
                .transport(transport)
                .build();

        ToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(List.of(mcpClient))
                .build();

        // MCP 工具让模型能查询 Git 仓库信息；这里的 prompt 只描述目标，不手写 GitHub API 调用。
        Bot bot = AiServices.builder(Bot.class)
                .chatModel(model)
                .toolProvider(toolProvider)
                .build();

        try {
            String response = bot.chat("Summarize the last 3 commits of the LangChain4j GitHub repository");
            System.out.println("RESPONSE: " + response);
        } finally {
            // 关闭 MCP client 会同步释放底层 Docker/stdio 通道资源。
            mcpClient.close();
        }
    }
}
