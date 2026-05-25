# 通用能力示例集合

## 模块定位

LangChain4j 常见能力的大杂烩模块，很适合在 tutorials 之后继续系统练习。

## 你可以学到什么

- Prompt Template
- Chat Memory
- 文档加载和文档问答
- AiService 接口式开发
- 工具调用和动态工具
- 持久化记忆
- Retriever 和 RAG
- Streaming 输出

## 建议先看这些代码

- `HelloWorldExample.java`：最小示例
- `PromptTemplateExamples.java`：提示词模板
- `ChatMemoryExamples.java`：聊天记忆
- `DocumentLoaderExamples.java`：文档加载
- `ChatWithDocumentsExamples.java`：文档问答
- `ServiceWithToolsExample.java`：工具调用
- `ServiceWithRetrieverExample.java`：检索增强
- `StreamingExamples.java`：流式输出

## 运行前准备

多数示例使用 OpenAI，运行前设置 OPENAI_API_KEY。

## 学习建议

如果不知道从哪个进阶主题开始，就按文件名从基础到工具、记忆、文档逐个运行。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
