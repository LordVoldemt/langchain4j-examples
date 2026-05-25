# Vertex AI Gemini 示例

## 模块定位

演示 Google Cloud Vertex AI Gemini 与 LangChain4j 的集成。

## 你可以学到什么

- Vertex AI Gemini ChatModel
- Google Cloud 项目和区域配置
- 服务账号或 gcloud 认证
- Vertex AI 与 Google AI Gemini 原生 API 的差异

## 建议先看这些代码

- `VertexAiGeminiChatModelExamples.java`：Gemini 聊天示例
- `VertexAiGeminiChatModelTest.java`：测试式调用示例

## 运行前准备

需要 Google Cloud 项目、Vertex AI 权限、区域和认证配置。

## 学习建议

如果你部署在 GCP 上，优先看这个模块；如果只是使用 Gemini API key，可以看 google-ai-gemini-examples。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
