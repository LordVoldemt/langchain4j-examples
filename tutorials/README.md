# 入门教程

## 模块定位

LangChain4j 的入门教程模块，适合第一次接触这个项目的新手。

## 你可以学到什么

- 创建第一个 ChatModel
- 配置模型参数
- 生成图片
- 使用 PromptTemplate
- 处理流式输出
- 加入 Chat Memory
- Few-shot 示例
- AiService 接口式开发
- 持久化记忆
- 工具调用
- 文档问答
- OpenAI-compatible 网关接入

## 建议先看这些代码

- `_00_HelloWorld.java`：第一个聊天示例
- `_01_ModelParameters.java`：模型参数
- `_02_OpenAiImageModelExamples.java`：图片生成
- `_03_PromptTemplate.java`：提示词模板
- `_04_Streaming.java`：流式响应
- `_05_Memory.java`：聊天记忆
- `_08_AIServiceExamples.java`：AiService
- `_10_ServiceWithToolsExample.java`：工具调用
- `_12_ChatWithDocumentsExamples.java`：文档问答
- `_13_OpenAiCompatibleExample.java`：OpenAI-compatible 聊天和图片接口

## 运行前准备

需要 Java 17 或更高版本。大多数示例使用 OpenAI，需要 OPENAI_API_KEY；OpenAI-compatible 示例使用 OPENAI_COMPATIBLE_BASE_URL、OPENAI_COMPATIBLE_API_KEY、OPENAI_COMPATIBLE_MODEL_NAME 和 OPENAI_COMPATIBLE_IMAGE_MODEL_NAME。

## 学习建议

建议按编号顺序阅读和运行，这是整个仓库最适合新手的入口。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
