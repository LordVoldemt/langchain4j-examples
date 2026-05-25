# OpenSearch 向量检索示例

## 模块定位

演示 OpenSearch 作为 LangChain4j 向量检索后端的用法。

## 你可以学到什么

- OpenSearch EmbeddingStore 配置
- 写入向量数据
- 执行相似度搜索
- 理解 OpenSearch 在 RAG 检索层中的角色

## 建议先看这些代码

- `OpenSearchEmbeddingStoreExample.java`：OpenSearch 向量存储示例

## 运行前准备

需要 OpenSearch 服务或集群，并根据示例配置连接信息。

## 学习建议

适合已经使用 OpenSearch 做搜索的项目参考。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
