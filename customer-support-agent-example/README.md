# 客服 Agent 完整示例

## 模块定位

一个更接近真实应用的客服 Agent 示例，整合了 Spring Boot、对话记忆、工具调用和 RAG。

## 你可以学到什么

- 在 Spring Boot 中组织 LangChain4j 应用
- 用 AiServices 定义客服助手
- 让 Agent 调用业务工具
- 把知识检索结果注入回答上下文
- 管理多轮对话和用户请求

## 建议先看这些代码

- `CustomerSupportAgentApplication.java`：应用入口
- `CustomerSupportAgent.java`：Agent 接口
- `CustomerSupportAgentConfiguration.java`：模型和检索配置
- `BookingTools.java`：业务工具
- `CustomerSupportAgentController.java`：HTTP 接口

## 运行前准备

运行前通常需要 OPENAI_API_KEY 或其他模型服务配置。

## 学习建议

这是新手从“示例代码”过渡到“业务应用”的重要模块，建议认真读配置类和 Controller。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
