# WildFly 示例

## 模块定位

演示在 WildFly / Jakarta EE 环境中使用 LangChain4j，并通过 SSE 输出流式响应。

## 你可以学到什么

- Jakarta EE 应用结构
- WildFly 中部署 AI 服务
- SSE 流式响应
- REST resource 与模型调用结合

## 建议先看这些代码

- `RestApplication.java`：应用入口
- `SseResource.java`：SSE 接口
- `SseBroadcasterStreamingResponseHandler.java`：流式响应处理

## 运行前准备

需要 WildFly 运行环境和模型 API key。

## 学习建议

适合已有 WildFly 应用、想加入 AI 聊天或流式输出能力的项目参考。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
