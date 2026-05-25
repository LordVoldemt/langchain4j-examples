# Google AlloyDB 向量检索示例

## 模块定位

演示 Google AlloyDB 作为向量存储时如何与 LangChain4j 配合。

## 你可以学到什么

- 连接 AlloyDB
- 使用数据库保存 Embedding 向量
- 基于 metadata 做过滤
- 把云数据库放进 RAG 检索层

## 建议先看这些代码

- `AlloyDBEmbeddingStoreWithMetadataExample.java`：AlloyDB 向量存储和元数据过滤

## 运行前准备

需要可访问的 AlloyDB 实例、数据库连接信息和必要扩展。

## 学习建议

适合已经在 Google Cloud 上使用 AlloyDB 的项目参考。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
