# OpenAI 示例

## 模块定位

演示 OpenAI 在 LangChain4j 中的主要能力，是理解模型提供商集成的核心参考模块之一。

## 你可以学到什么

- 基础聊天和请求参数
- 流式输出
- Embedding
- 函数调用
- 图片生成
- 音频转录
- 请求/响应可观测性

## 建议先看这些代码

- `OpenAiChatModelExamples.java`：聊天和参数
- `OpenAiStreamingChatModelExamples.java`：流式输出
- `OpenAiEmbeddingModelExamples.java`：Embedding
- `OpenAiFunctionCallingExamples.java`：函数调用
- `OpenAiImageModelExamples.java`：图片生成
- `OpenAiAudioModelExamples.java`：音频转录
- `OpenAiObservabilityExamples.java`：可观测性

## 运行前准备

运行前设置 OPENAI_API_KEY。OpenAI-compatible 网关可参考 tutorials/_13_OpenAiCompatibleExample.java。

## 学习建议

建议先读 ChatModel，再看 Streaming、Embedding、Tools，最后看图片和音频。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
