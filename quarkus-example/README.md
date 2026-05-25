# Quarkus 示例

## 模块定位

演示在 Quarkus 应用中集成 LangChain4j，示例场景偏向文本评估和 Review 分流。

## 你可以学到什么

- Quarkus REST resource 中调用 AI 服务
- 用服务类封装模型逻辑
- 让模型输出结构化结果
- 在业务对象中承载 AI 判断结果

## 建议先看这些代码

- `ReviewResource.java`：REST 入口
- `TriageService.java`：业务服务
- `Evaluation.java`：评估结果
- `TriagedReview.java`：分流结果
- `ImportmapResource.java`：前端资源入口

## 运行前准备

需要 Quarkus 运行环境和模型 API key。

## 学习建议

适合 Quarkus 用户参考 AI 能力如何进入已有 REST 服务。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
