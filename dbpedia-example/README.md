# DBpedia 知识库示例

## 模块定位

演示如何结合 DBpedia / SPARQL 这类结构化知识库与大模型能力。

## 你可以学到什么

- 通过 SPARQL 查询外部知识库
- 把结构化知识作为回答依据
- 理解知识库查询和 LLM 生成的分工
- 参考 Azure OpenAI 配置方式

## 建议先看这些代码

- `DbPediaSparqlExample.java`：DBpedia SPARQL 查询示例
- `AzureOpenAIConfig.java`：Azure OpenAI 配置辅助类

## 运行前准备

需要能访问 DBpedia 查询端点，并根据示例配置 Azure OpenAI 或其他模型服务。

## 学习建议

适合对知识图谱、结构化知识问答感兴趣的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
