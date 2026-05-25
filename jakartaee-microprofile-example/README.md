# Jakarta EE / MicroProfile 示例

## 模块定位

演示在 Jakarta EE 和 MicroProfile 应用中集成 LangChain4j。

## 你可以学到什么

- 通过 REST resource 暴露 AI 能力
- 用 Service 封装模型调用
- 在 Jakarta EE 容器中组织依赖
- 编写集成测试验证接口

## 建议先看这些代码

- `ModelResource.java`：模型接口入口
- `ChatService.java`：聊天服务
- `ChatAgent.java`：Agent 定义
- `RestApplication.java`：应用配置
- `src/test/java`：接口测试示例

## 运行前准备

需要 Jakarta EE / MicroProfile 运行环境，运行方式请结合 pom.xml 和已有 README。

## 学习建议

适合企业 Java 应用迁移或接入 LLM 能力时参考。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
