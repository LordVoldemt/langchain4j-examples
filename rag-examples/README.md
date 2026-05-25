# RAG 示例集合

## 模块定位

集中展示 RAG（检索增强生成）的基础实现和高级技巧，是学习 LangChain4j RAG 的主入口。

## 你可以学到什么

- Easy RAG 和 Naive RAG 的差异
- 查询压缩
- 查询路由
- 检索结果重排
- metadata 和过滤
- 多个检索器组合
- Web Search 增强
- 返回引用来源
- SQL 数据库检索

## 建议先看这些代码

- `Easy_RAG_Example.java`：最容易上手的 RAG
- `Naive_RAG_Example.java`：手动搭建基础 RAG
- `_01_Advanced_RAG_with_Query_Compression_Example.java`：查询压缩
- `_02_Advanced_RAG_with_Query_Routing_Example.java`：查询路由
- `_03_Advanced_RAG_with_ReRanking_Example.java`：重排
- `_05_Advanced_RAG_with_Metadata_Filtering_Examples.java`：元数据过滤
- `_09_Advanced_RAG_Return_Sources_Example.java`：返回来源

## 运行前准备

多数示例需要模型 API key，例如 OPENAI_API_KEY。部分示例可能还需要外部搜索或数据库配置。

## 学习建议

建议先跑 Easy_RAG_Example，再看 Naive_RAG_Example，最后按编号学习高级技巧。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
