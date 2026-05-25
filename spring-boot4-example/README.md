# Spring Boot 4 示例

## 模块定位

演示 Spring Boot 4 项目中集成 LangChain4j 的方式，结构与 spring-boot-example 类似。

## 你可以学到什么

- Spring Boot 4 应用结构
- Assistant 接口式开发
- REST Controller 调用模型
- 流式响应
- 工具调用和监听器

## 建议先看这些代码

- `ExampleApplication.java`：应用入口
- `Assistant.java`：Assistant 接口
- `AssistantController.java`：REST 入口
- `AssistantConfiguration.java`：配置类
- `StreamingAssistant.java`：流式接口
- `ChatModelController.java`：模型调用接口

## 运行前准备

需要 Spring Boot 4 兼容环境和模型 API key。

## 学习建议

如果你的项目已经升级到 Spring Boot 4，优先参考这个模块。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
