# Vespa 向量检索示例

## 模块定位

演示 Vespa 与 LangChain4j 的向量检索集成。

## 你可以学到什么

- Vespa EmbeddingStore
- 搜索引擎和向量搜索结合
- 把 Vespa 作为 RAG 检索层
- 理解 schema / index 对检索的影响

## 建议先看这些代码

- `VespaEmbeddingStoreExample.java`：Vespa 向量存储示例
- `src/main/java/README.md`：模块内补充说明

## 运行前准备

需要 Vespa 服务和对应 schema 配置。

## 学习建议

适合关注搜索工程、混合检索和生产检索系统的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
