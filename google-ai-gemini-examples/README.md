# Google AI Gemini 示例

## 模块定位

演示 Google AI Gemini 原生 API 在 LangChain4j 中的使用方式。

## 你可以学到什么

- Gemini 基础聊天和流式聊天
- 生成文本 Embedding
- 统计 token
- 返回 JSON 结构化内容
- 使用工具调用
- 处理图片和文件等多模态输入
- 使用 Gemini 批处理能力

## 建议先看这些代码

- `Example01_SimpleChat.java`：基础聊天
- `Example02_StreamingChat.java`：流式输出
- `Example03_SimpleEmbedding.java`：Embedding
- `Example05_ChatWithJsonResponse.java`：JSON 输出
- `Example06_ChatWithTools.java`：工具调用
- `Example09_MultimodalChat.java`：多模态聊天
- `Example12_BatchChatInline.java`：批处理聊天

## 运行前准备

需要 Java 17、Maven 和 GOOGLE_AI_GEMINI_API_KEY。

## 学习建议

建议按 Example 编号顺序阅读，从简单聊天逐步走到多模态和批处理。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
