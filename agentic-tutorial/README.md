# Agentic 教程

## 模块定位

学习 LangChain4j Agentic API 的完整教程模块，重点不是单次聊天，而是把多个 Agent 组织成可控、可观察、可扩展的工作流。

## 你可以学到什么

- 定义基础 Agent，并让它返回字符串或结构化对象
- 把多个 Agent 串成顺序、并行、循环和条件分支流程
- 使用 Supervisor 模式协调多个子 Agent
- 在工作流中读取和传递 AgenticScope 状态
- 加入 Human-in-the-loop，让关键步骤由人确认

## 建议先看这些代码

- `src/main/java/_1_basic_agent`：最小 Agent 示例
- `src/main/java/_2_sequential_workflow`：顺序工作流
- `src/main/java/_4_parallel_workflow`：并行执行多个 Agent
- `src/main/java/_7_supervisor_orchestration`：Supervisor 编排
- `src/main/java/README.md`：更细的示例索引

## 运行前准备

运行前通常需要配置 OPENAI_API_KEY。也可以参考 util/ChatModelProvider.java，把模型切换成 Cerebras 或其他 OpenAI-compatible 服务。

## 学习建议

如果你已经理解 tutorials 里的基础调用，可以从这里继续学习多 Agent 应用怎么组织。

## 常见改造方向

- 把示例中的模型配置改成你正在使用的模型服务。
- 把硬编码的示例输入改成命令行参数、HTTP 参数或配置文件。
- 如果示例使用外部数据库或向量库，先用最小数据集跑通写入和检索流程。
- 跑通后再加入日志、异常处理和更贴近业务的 prompt。
