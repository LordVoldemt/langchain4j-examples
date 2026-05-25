# Helidon 示例集合

## 模块定位

包含 Helidon SE 和 Helidon MP 两个咖啡店助手示例，展示在 Helidon 应用中集成 LangChain4j。

## 你可以学到什么

- Helidon SE 集成方式
- Helidon MP 集成方式
- REST 服务调用 AI 助手
- 用业务服务包装菜单、订单等领域逻辑

## 建议先看这些代码

- `coffee-shop-assistant-se`：Helidon SE 版本
- `coffee-shop-assistant-mp`：Helidon MP 版本
- `子目录 README.md`：各自运行说明

## 运行前准备

进入具体子目录运行。通常需要模型 API key，例如 OPENAI_API_KEY。

## 学习建议

如果你的 Java 服务使用 Helidon，这个模块比 Spring 示例更贴近你的技术栈。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
