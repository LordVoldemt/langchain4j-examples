# Ollama 本地模型示例

## 模块定位

演示如何通过 LangChain4j 调用 Ollama 本地模型。

## 你可以学到什么

- 本地模型聊天调用
- Ollama 流式输出
- 使用容器准备 Ollama 测试环境
- 本地模型和云模型在 LangChain4j 抽象下的相同点

## 建议先看这些代码

- `OllamaChatModelTest.java`：普通聊天
- `OllamaStreamingChatModelTest.java`：流式输出
- `AbstractOllamaInfrastructure.java`：测试基础设施
- `LangChain4jOllamaContainer.java`：容器封装

## 运行前准备

需要本地 Ollama 或 Docker/Testcontainers 环境，并准备对应模型。

## 学习建议

适合想离线开发、降低 API 成本或尝试本地模型的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
