# Amazon Bedrock 示例

## 模块定位

演示如何通过 LangChain4j 调用 Amazon Bedrock 上的大模型。

## 你可以学到什么

- 创建 Bedrock ChatModel
- 使用 Bedrock StreamingChatModel
- 理解 AWS 区域、凭据和模型权限对调用的影响

## 建议先看这些代码

- `BedrockChatModelExample.java`：普通聊天
- `BedrockStreamingChatModelExample.java`：流式聊天

## 运行前准备

需要 AWS 凭据、AWS_REGION，并确认账号已经开通目标 Bedrock 模型访问权限。

## 学习建议

适合已经在 AWS 上部署应用，想把 LangChain4j 接入 Bedrock 的同学。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
