# MCP GitHub 示例

## 模块定位

演示通过 MCP 让 LangChain4j Agent 调用 GitHub 能力。

## 你可以学到什么

- 连接 GitHub MCP 工具
- 让模型通过工具读取或操作 GitHub 数据
- 理解 MCP 工具和普通 Java @Tool 的差异
- 把外部 SaaS 能力接入 Agent

## 建议先看这些代码

- `Bot.java`：Bot 定义
- `McpGithubToolsExample.java`：GitHub MCP 调用示例

## 运行前准备

需要 GitHub token，以及可用的 GitHub MCP server 配置。

## 学习建议

适合学完 mcp-example 后继续阅读，理解真实工具集成。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
