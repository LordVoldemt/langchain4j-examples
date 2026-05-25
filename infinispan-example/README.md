# Infinispan 向量存储示例

## 模块定位

演示如何把 Infinispan 用作 LangChain4j 的向量存储。

## 你可以学到什么

- 配置 Infinispan EmbeddingStore
- 写入文本向量
- 进行相似度检索
- 理解缓存/数据网格与 RAG 检索的关系

## 建议先看这些代码

- `InfinispanEmbeddingStoreExample.java`：完整 Infinispan 向量存储示例

## 运行前准备

需要 Infinispan 服务或对应容器环境。

## 学习建议

适合已经使用 Infinispan 的 Java 项目参考。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
