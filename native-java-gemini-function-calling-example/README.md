# 原生 Java Gemini 函数调用示例

## 模块定位

演示不依赖完整框架时，如何在 Java 中使用 Gemini function calling，并和 LangChain4j 思路对照。

## 你可以学到什么

- Gemini 函数调用基础
- 工具 schema 与 Java 方法之间的关系
- 原生 Java 调用方式
- 和 LangChain4j 工具调用抽象的区别

## 建议先看这些代码

- `Langchain4JFunctionCallingApplication.java`：应用入口和核心示例

## 运行前准备

需要 Gemini API key，通常通过 GOOGLE_AI_GEMINI_API_KEY 配置。

## 学习建议

适合已经看过 google-ai-gemini-examples 中工具调用示例后继续对比。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
