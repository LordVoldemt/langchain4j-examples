package dev.langchain4j.example.mcp;

public interface Bot {

    // AiServices 会为这个接口生成代理；真正可用的工具来自 MCP ToolProvider。
    String chat(String prompt);
}
