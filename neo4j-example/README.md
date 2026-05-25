# Neo4j 图数据库示例

## 模块定位

演示 Neo4j 与 LangChain4j 的集成，覆盖向量存储、内容检索和 Spring Boot 示例。

## 你可以学到什么

- Neo4j EmbeddingStore
- Neo4j ContentRetriever
- 图数据库与 RAG 结合
- 在 Spring Boot 中暴露检索能力

## 建议先看这些代码

- `Neo4jEmbeddingStoreExample.java`：Neo4j 向量存储
- `Neo4jContentRetrieverExample.java`：内容检索器
- `SpringBootExample.java`：Spring Boot 集成
- `EmbeddingController.java`：HTTP 接口

## 运行前准备

需要 Neo4j 实例，并配置连接地址、用户名和密码。

## 学习建议

适合想把知识图谱和语义检索结合起来的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
