# OVH AI 示例

## 模块定位

演示 OVH AI 与 LangChain4j 的 Embedding 和 RAG 集成。

## 你可以学到什么

- 调用 OVH AI Embedding
- 生成文本向量
- 用 OVH AI Embedding 搭建基础 RAG
- 理解模型服务和向量存储的组合方式

## 建议先看这些代码

- `OvhAiEmbeddingSimpleExample.java`：简单 Embedding
- `OvhAiEmbeddingRAGExample.java`：Embedding + RAG

## 运行前准备

需要 OVH AI 相关凭据和服务地址。

## 学习建议

适合想尝试 OpenAI 之外 Embedding 服务的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
