# Jlama 本地推理示例

## 模块定位

演示 Jlama 与 LangChain4j 的集成，用 Java 本地运行大模型能力。

## 你可以学到什么

- Jlama 基础聊天
- Jlama 流式输出
- 本地 RAG 示例
- 函数调用
- Java 本地推理的 JVM 参数要求

## 建议先看这些代码

- `JlamaChatModelExamples.java`：聊天模型
- `JlamaStreamingChatModelExamples.java`：流式输出
- `JlamaBasicRagEmbedExamples.java`：RAG 示例
- `JlamaAiFunctionCallingExamples.java`：函数调用

## 运行前准备

通常需要 Java 20+，并添加 Vector API / native access / preview 等 JVM 参数。具体请看原模块说明。

## 学习建议

适合探索本地推理和纯 Java AI 应用的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
