# Mistral AI 示例

## 模块定位

演示 Mistral AI 与 LangChain4j 的集成，包括聊天、流式、函数调用和 RAG。

## 你可以学到什么

- Mistral 聊天模型
- 流式响应
- 函数调用
- Embedding 与基础 RAG

## 建议先看这些代码

- `MistralAiChatModelExamples.java`：聊天模型
- `MistralAiStreamingChatModelExamples.java`：流式输出
- `MistralAiFunctionCallingExamples.java`：函数调用
- `MistralAiBasicRagEmbedExamples.java`：Embedding + RAG

## 运行前准备

需要 Mistral AI API key。具体环境变量请查看示例源码。

## 学习建议

适合想使用欧洲模型服务或比较不同模型提供商的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
