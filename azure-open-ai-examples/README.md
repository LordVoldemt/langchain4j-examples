# Azure OpenAI 基础示例

## 模块定位

集中演示 Azure OpenAI 在 LangChain4j 中的常见能力，包括聊天、流式、Embedding、函数调用和图片生成。

## 你可以学到什么

- 配置 Azure OpenAI ChatModel
- 使用流式聊天响应
- 调用 Azure OpenAI Embedding 模型
- 让模型触发函数调用
- 使用 Azure OpenAI 的图片生成能力
- 了解安全相关配置

## 建议先看这些代码

- `AzureOpenAiChatModelExamples.java`：聊天模型
- `AzureOpenAiStreamingChatModelExamples.java`：流式输出
- `AzureOpenAiEmbeddingModelExamples.java`：Embedding
- `AzureOpenAiFunctionCallingExamples.java`：函数调用
- `AzureOpenAiDallEExample.java`：图片生成
- `AzureOpenAiSecurityExamples.java`：安全配置

## 运行前准备

需要 Azure OpenAI 资源、deployment、endpoint 和 API key。不同示例可能读取不同环境变量，运行前请打开对应文件确认。

## 学习建议

如果只是想先理解 LangChain4j 基础抽象，建议先看 tutorials，再回来看 Azure 的配置差异。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
