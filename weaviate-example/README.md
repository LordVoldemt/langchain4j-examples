# Weaviate 向量数据库示例

## 模块定位

演示 Weaviate 与 LangChain4j 的向量检索集成。

## 你可以学到什么

- Weaviate EmbeddingStore
- 写入文本向量
- 执行相似度搜索
- 把 Weaviate 接入 RAG 检索层

## 建议先看这些代码

- `WeaviateEmbeddingStoreExample.java`：Weaviate 向量存储示例

## 运行前准备

需要 Weaviate 服务，可以使用 Docker、本地实例或云服务。

## 学习建议

适合对向量数据库生态做对比学习的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
