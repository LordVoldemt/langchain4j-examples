# Qdrant 示例

## 模块定位

演示 Qdrant 向量数据库与 LangChain4j 的集成。

## 你可以学到什么

- 配置 Qdrant EmbeddingStore
- 写入向量
- 相似度搜索
- 理解 collection 在向量库中的作用

## 建议先看这些代码

- `QdrantEmbeddingStoreExample.java`：Qdrant 向量存储示例

## 运行前准备

需要 Qdrant 服务，可以用 Docker 启动本地实例。

## 学习建议

适合比较不同向量数据库的 Java 接入方式。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
