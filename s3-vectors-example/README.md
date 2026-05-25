# S3 Vectors 示例

## 模块定位

演示 AWS S3 Vectors 与 LangChain4j 的集成。

## 你可以学到什么

- S3 Vectors 作为向量存储
- 写入和检索向量
- 使用 metadata 做过滤
- 理解 AWS 存储服务在 RAG 中的可能用法

## 建议先看这些代码

- `S3VectorsEmbeddingStoreExample.java`：基础示例
- `S3VectorsEmbeddingStoreWithMetadataExample.java`：带 metadata 示例

## 运行前准备

需要 AWS 凭据、区域和 S3 Vectors 相关资源权限。

## 学习建议

适合 AWS 技术栈用户了解新的向量存储选择。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
