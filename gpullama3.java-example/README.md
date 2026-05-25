# gpullama3.java 本地模型示例

## 模块定位

演示通过 gpullama3.java 在 Java 中运行或调用本地 Llama 系列模型，并与 LangChain4j Agentic 示例结合。

## 你可以学到什么

- 本地模型聊天调用
- 本地模型流式输出
- 把本地模型作为 Agent 底座
- 复用 Agentic 教程中的工作流

## 建议先看这些代码

- `GPULlama3ChatModelExample.java`：基础聊天
- `GPULlama3StreamingChatModelExample.java`：流式输出
- `GPULlama3ChatModelProvider.java`：模型创建
- `GPULlama3_1a_Basic_Agent_Example.java`：基础 Agent

## 运行前准备

需要满足 gpullama3.java 的本地运行环境，通常还需要准备模型文件和 GPU/运行时配置。

## 学习建议

适合想减少云 API 依赖、探索本地模型的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
