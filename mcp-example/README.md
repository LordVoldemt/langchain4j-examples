# MCP 基础示例

## 模块定位

演示 LangChain4j 与 MCP（Model Context Protocol）的基础集成。

## 你可以学到什么

- MCP 工具的调用方式
- stdio 与 HTTP 两种 MCP 连接方式
- 让 Agent 使用外部工具
- 理解 MCP 在工具生态中的位置

## 建议先看这些代码

- `Bot.java`：使用工具的 Bot
- `McpToolsExampleOverStdio.java`：stdio MCP 示例
- `McpToolsExampleOverHttp.java`：HTTP MCP 示例

## 运行前准备

需要先准备对应 MCP server，并确认 stdio 或 HTTP 连接配置。

## 学习建议

理解本模块后，可以继续看 mcp-github-example，学习连接真实外部系统。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
