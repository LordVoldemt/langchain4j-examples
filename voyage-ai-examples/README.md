# Voyage AI 示例

## 模块定位

演示 Voyage AI 在 Embedding 和 Scoring / reranking 场景中的用法。

## 你可以学到什么

- 调用 Voyage AI Embedding 模型
- 使用 Voyage AI Scoring 模型
- 理解向量召回与重排的关系
- 把 reranking 加入 RAG 流程

## 建议先看这些代码

- `VoyageAiEmbeddingModelExample.java`：Embedding 示例
- `VoyageAiScoringModelExample.java`：Scoring 示例

## 运行前准备

需要 Voyage AI API key。

## 学习建议

建议与 rag-examples 中的重排示例一起阅读。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
