# Payara Micro 示例

## 模块定位

演示在 Payara Micro 中通过 REST API 暴露多种模型服务调用。

## 你可以学到什么

- Payara Micro 应用结构
- REST resource 调用模型
- OpenAI、Gemini、DeepSeek 等服务资源拆分
- 前端页面调用后端 AI 接口

## 建议先看这些代码

- `OpenAiChatModelResource.java`：OpenAI 聊天接口
- `GeminiChatModelResource.java`：Gemini 聊天接口
- `DeepSeekChatModelResource.java`：DeepSeek 聊天接口
- `RestConfiguration.java`：配置类
- `src/main/webapp/index.html`：简单前端页面

## 运行前准备

需要 Payara Micro 运行环境，并配置对应模型服务的 API key。

## 学习建议

适合 Jakarta EE / MicroProfile 用户继续参考。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
