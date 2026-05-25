package dev.langchain4j.example.mcp.github;

public interface Bot {

    // 这是面向应用代码的聊天入口；GitHub 能力通过 MCP 注入，不写在接口签名里。
    String chat(String prompt);
}
