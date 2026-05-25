# JavaFX 桌面应用示例

## 模块定位

演示如何在 JavaFX 桌面端应用里接入 LangChain4j。

## 你可以学到什么

- JavaFX UI 与模型调用结合
- 流式响应在桌面界面中的展示
- 把搜索或工具动作接入聊天体验
- 拆分 UI、服务和模型调用逻辑

## 建议先看这些代码

- `ChatApp.java`：主界面
- `AnswerService.java`：回答服务
- `Assistant.java`：助手接口
- `CustomStreamingResponseHandler.java`：流式处理
- `SearchAction.java`：搜索动作

## 运行前准备

需要 JavaFX 运行环境和模型 API key。

## 学习建议

适合想做桌面 AI 助手或内部工具的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
