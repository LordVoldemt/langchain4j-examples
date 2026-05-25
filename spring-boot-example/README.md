# Spring Boot 示例

## 模块定位

演示在 Spring Boot 应用中集成 LangChain4j 的常见方式。

## 你可以学到什么

- Spring Boot 应用中配置 ChatModel
- 用 Assistant 接口定义 AI 能力
- 通过 Controller 暴露普通和流式接口
- 使用工具调用
- 监听模型请求和响应

## 建议先看这些代码

- `ExampleApplication.java`：应用入口
- `Assistant.java`：Assistant 接口
- `AssistantController.java`：REST 入口
- `AssistantConfiguration.java`：模型配置
- `AssistantTools.java`：工具方法
- `StreamingAssistant.java`：流式 Assistant
- `MyChatModelListener.java`：模型监听器

## 运行前准备

需要 Spring Boot 环境和模型 API key。

## 学习建议

这是 Web 应用新手最推荐的框架集成入口之一。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
