# YugabyteDB 向量检索示例

## 模块定位

演示 YugabyteDB 与 LangChain4j 向量检索能力的集成。

## 你可以学到什么

- YugabyteDB EmbeddingStore
- PostgreSQL 驱动连接方式
- Smart Driver 连接方式
- 带 metadata 的向量检索
- 分布式 SQL 数据库中的 RAG 检索层

## 建议先看这些代码

- `YugabyteDBEmbeddingStoreExample.java`：基础向量存储
- `YugabyteDBEmbeddingStoreWithMetadataExample.java`：带 metadata 检索
- `YugabyteDBWithPostgreSQLDriverExample.java`：PostgreSQL 驱动
- `YugabyteDBWithSmartDriverExample.java`：Smart Driver

## 运行前准备

需要 YugabyteDB 实例，并配置连接地址、用户名、密码和数据库信息。

## 学习建议

适合对分布式数据库和向量检索结合感兴趣的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
