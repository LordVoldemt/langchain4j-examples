# Anthropic Claude 示例

## 模块定位

演示如何在 LangChain4j 中接入 Anthropic Claude，适合想比较 Claude 与 OpenAI 用法差异的新手。

## 你可以学到什么

- 创建 Claude ChatModel 并发送普通消息
- 使用 StreamingChatModel 接收流式回复
- 让 Claude 调用 Java 工具方法
- 理解不同模型提供商在 LangChain4j 中的统一抽象

## 建议先看这些代码

- `src/main/java/AnthropicChatModelTest.java`：普通聊天
- `src/main/java/AnthropicStreamingChatModelTest.java`：流式输出
- `src/main/java/AnthropicToolsTest.java`：工具调用

## 运行前准备

运行前需要 Anthropic API Key，通常通过 ANTHROPIC_API_KEY 配置。类名带 Test，但主要承担示例作用。

## 学习建议

建议先跑普通聊天，再看流式输出，最后看工具调用。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
