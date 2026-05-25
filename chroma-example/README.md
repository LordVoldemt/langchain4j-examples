# Chroma 向量库示例

## 模块定位

演示如何把 Chroma 作为 LangChain4j 的 EmbeddingStore，用于 RAG 检索层。

## 你可以学到什么

- 把文本转换为向量并写入 Chroma
- 执行相似度搜索
- 给向量数据添加 metadata
- 理解 EmbeddingModel 与 EmbeddingStore 的配合关系

## 建议先看这些代码

- `ChromaEmbeddingStoreExample.java`：基础写入和检索
- `ChromaEmbeddingStoreWithMetadataExample.java`：带 metadata 的检索

## 运行前准备

示例通常依赖 Docker/Testcontainers 启动 Chroma。运行前请确认 Docker 可用。

## 学习建议

先理解 rag-examples 里的 RAG 流程，再看这个模块能更容易明白向量库的位置。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
