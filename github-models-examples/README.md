# GitHub Models 示例

## 模块定位

演示如何通过 LangChain4j 调用 GitHub Models。

## 你可以学到什么

- GitHub Models ChatModel 调用
- 流式响应
- Embedding 模型调用
- 函数调用

## 建议先看这些代码

- `GitHubModelsChatModelExamples.java`：聊天模型
- `GitHubModelsStreamingChatModelExamples.java`：流式输出
- `GitHubModelsEmbeddingModelExamples.java`：Embedding
- `GitHubModelsFunctionCallingExamples.java`：函数调用

## 运行前准备

需要 GitHub token，并确认账号有 GitHub Models 访问权限。

## 学习建议

适合想在 GitHub 生态中快速体验模型能力的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
