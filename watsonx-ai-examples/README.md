# IBM watsonx.ai 示例

## 模块定位

演示 IBM watsonx.ai 与 LangChain4j 的多种集成能力。

## 你可以学到什么

- watsonx.ai 聊天模型
- Reasoning 模型调用
- 流式响应
- Embedding
- Moderation
- Scoring
- Token 统计
- 工具调用

## 建议先看这些代码

- `WatsonxChatModelTest.java`：聊天模型
- `WatsonxChatModelReasoningTest.java`：Reasoning 聊天
- `WatsonxStreamingChatModelTest.java`：流式输出
- `WatsonxEmbeddingModelTest.java`：Embedding
- `WatsonxModerationModelTest.java`：内容审核
- `WatsonxScoringModelTest.java`：Scoring
- `WatsonxToolsTest.java`：工具调用

## 运行前准备

需要 watsonx.ai URL、project id、API key 等配置。

## 学习建议

适合 IBM Cloud / 企业 AI 平台用户参考。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
