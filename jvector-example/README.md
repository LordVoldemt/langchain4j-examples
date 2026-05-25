# JVector 向量检索示例

## 模块定位

演示 JVector 与 LangChain4j 的向量检索集成。

## 你可以学到什么

- 使用本地向量索引
- 写入 Embedding
- 执行相似度搜索
- 理解不依赖外部向量数据库的 RAG 流程

## 建议先看这些代码

- `JVectorEmbeddingStoreExample.java`：JVector 向量存储示例

## 运行前准备

通常无需外部数据库，适合本地学习 EmbeddingStore 的基本流程。

## 学习建议

先看这个模块，再对比 Chroma、Qdrant、Milvus 等外部向量数据库，会更容易理解差异。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
