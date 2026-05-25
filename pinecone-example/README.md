# Pinecone 示例

## 模块定位

演示 Pinecone 向量数据库与 LangChain4j 的集成。

## 你可以学到什么

- 连接 Pinecone
- 创建或使用已有 index
- 写入向量数据
- 执行语义检索

## 建议先看这些代码

- `PineconeEmbeddingStoreExample.java`：Pinecone 向量存储示例

## 运行前准备

需要 Pinecone API key、index 名称和环境配置。

## 学习建议

适合想使用托管向量数据库、快速搭建云端 RAG 的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
