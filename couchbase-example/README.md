# Couchbase 向量检索示例

## 模块定位

演示 Couchbase 与 LangChain4j 向量检索能力的集成。

## 你可以学到什么

- 连接 Couchbase 并作为向量存储使用
- 写入文本向量
- 执行相似度搜索
- 把 Couchbase 放进 RAG 检索链路

## 建议先看这些代码

- `CouchbaseEmbeddingSearchExample.java`：完整 Couchbase 向量搜索流程

## 运行前准备

需要可访问的 Couchbase 实例，并配置连接地址、用户名、密码、bucket 等信息。

## 学习建议

适合已经使用 Couchbase 的项目参考如何加入语义检索能力。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
