# Azure OpenAI 客服 Agent 示例

## 模块定位

一个使用 Azure OpenAI 的客服 Agent 业务示例，展示如何把聊天模型、业务工具和领域对象组合成一个可运行的小应用。

## 你可以学到什么

- 使用 Azure OpenAI 作为底层模型
- 用 AiServices 定义客服 Agent 接口
- 把 BookingTools 暴露给 Agent 调用
- 模拟客服场景中的订单查询、取消和异常处理
- 理解业务对象、服务类和 Agent 的职责分层

## 建议先看这些代码

- `CustomerSupportAgentApplication.java`：应用入口
- `CustomerSupportAgent.java`：客服 Agent 定义
- `BookingTools.java`：Agent 可调用的工具
- `BookingService.java`：模拟业务服务
- `Booking.java / Customer.java`：领域对象

## 运行前准备

运行前需要 Azure OpenAI endpoint、deployment name 和 API key。请先查看配置类或 pom.xml 中的依赖。

## 学习建议

读完 customer-support-agent-example 后，再看这个模块，可以理解同一个业务场景如何迁移到 Azure OpenAI。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
