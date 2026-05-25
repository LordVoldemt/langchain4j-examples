# Elasticsearch 向量检索示例

## 模块定位

演示如何使用 Elasticsearch 作为 LangChain4j 的向量检索后端。

## 你可以学到什么

- 配置 Elasticsearch EmbeddingStore
- 写入和检索向量
- 使用脚本进行向量相似度计算
- 理解搜索引擎与 RAG 的结合方式

## 建议先看这些代码

- `ElasticsearchEmbeddingStoreExample.java`：基础向量存储
- `ElasticsearchEmbeddingStoreWithScriptExample.java`：脚本检索示例

## 运行前准备

需要 Elasticsearch 服务。部分示例可能使用 Testcontainers，运行前确认 Docker 或连接配置可用。

## 学习建议

如果项目已经使用 Elasticsearch，可以参考本模块把关键词搜索扩展为语义检索。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
