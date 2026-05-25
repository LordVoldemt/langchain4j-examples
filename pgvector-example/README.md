# pgvector 示例

## 模块定位

演示 PostgreSQL + pgvector 作为 LangChain4j 向量存储的用法。

## 你可以学到什么

- 配置 PgVectorEmbeddingStore
- 把文本向量写入 PostgreSQL
- 执行语义相似度搜索
- 使用 metadata 过滤检索结果
- 用关系型数据库搭建 RAG 检索层

## 建议先看这些代码

- `PgVectorEmbeddingStoreExample.java`：基础 pgvector 示例
- `PgVectorEmbeddingStoreWithMetadataExample.java`：带 metadata 检索

## 运行前准备

需要 PostgreSQL，并安装/启用 pgvector 扩展。

## 学习建议

适合已经使用 PostgreSQL 的项目，是非常实用的 RAG 入门后端。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
