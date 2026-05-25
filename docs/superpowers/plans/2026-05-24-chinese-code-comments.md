# 中文代码注释补充计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 为仓库内 Java 示例代码补充面向新手的中文注释，帮助读者理解示例意图、关键配置和运行注意点。

**Architecture:** 按模块组并行处理，每个 worker 只修改自己负责的目录。注释以解释“为什么这样写”和“这段示例演示什么”为主，不做逐行翻译，不改变任何代码行为。

**Tech Stack:** Java 17、Maven、LangChain4j、多模块示例项目。

---

## 注释原则

- [ ] 使用中文注释，优先解释示例目标、关键配置、外部依赖、模型调用流程和容易踩坑的位置。
- [ ] 不给简单赋值、getter/setter、显而易见的 import 添加注释。
- [ ] 不改变业务逻辑、类名、方法名、包名、依赖版本或格式化风格。
- [ ] 不写入真实 API key、token、密码或个人信息。
- [ ] 对已经很清晰的代码保持克制，避免注释噪音。
- [ ] 每个 Java 文件至少检查一遍；如果文件已经足够清晰，可以只添加类级或关键方法级注释。

## 并行任务划分

### Task 1: 入门与通用示例

**Files:**
- Modify: `tutorials/**/*.java`
- Modify: `other-examples/**/*.java`
- Modify: `rag-examples/**/*.java`

- [ ] 为教程示例补充“本示例学习目标”的类级注释。
- [ ] 为 RAG 示例补充检索、重排、路由、metadata 等关键概念注释。
- [ ] 为通用能力示例补充 Prompt、Memory、Tools、Retriever 等学习提示。

### Task 2: 模型提供商示例

**Files:**
- Modify: `open-ai-examples/**/*.java`
- Modify: `anthropic-examples/**/*.java`
- Modify: `azure-open-ai-examples/**/*.java`
- Modify: `bedrock-examples/**/*.java`
- Modify: `github-models-examples/**/*.java`
- Modify: `google-ai-gemini-examples/**/*.java`
- Modify: `mistral-ai-examples/**/*.java`
- Modify: `ollama-examples/**/*.java`
- Modify: `vertex-ai-gemini-examples/**/*.java`
- Modify: `voyage-ai-examples/**/*.java`
- Modify: `watsonx-ai-examples/**/*.java`
- Modify: `ovh-ai-examples/**/*.java`

- [ ] 为不同模型提供商的配置点补充中文注释。
- [ ] 解释 API key、baseUrl、modelName、deployment 等关键字段。
- [ ] 标注流式、Embedding、工具调用、图片/音频等示例重点。

### Task 3: 向量库与数据库示例

**Files:**
- Modify: `chroma-example/**/*.java`
- Modify: `couchbase-example/**/*.java`
- Modify: `elasticsearch-example/**/*.java`
- Modify: `google-alloydb-example/**/*.java`
- Modify: `infinispan-example/**/*.java`
- Modify: `jvector-example/**/*.java`
- Modify: `milvus-example/**/*.java`
- Modify: `neo4j-example/**/*.java`
- Modify: `opensearch-example/**/*.java`
- Modify: `oracle-example/**/*.java`
- Modify: `pgvector-example/**/*.java`
- Modify: `pinecone-example/**/*.java`
- Modify: `qdrant-example/**/*.java`
- Modify: `redis-example/**/*.java`
- Modify: `s3-vectors-example/**/*.java`
- Modify: `vespa-example/**/*.java`
- Modify: `weaviate-example/**/*.java`
- Modify: `yugabytedb-example/**/*.java`

- [ ] 为 EmbeddingStore 的初始化、写入、查询补充中文注释。
- [ ] 解释 metadata、collection/index/table、Testcontainers 或外部服务依赖。
- [ ] 对数据库连接和向量检索步骤添加新手提示。

### Task 4: 框架集成示例

**Files:**
- Modify: `spring-boot-example/**/*.java`
- Modify: `spring-boot4-example/**/*.java`
- Modify: `quarkus-example/**/*.java`
- Modify: `helidon-examples/**/*.java`
- Modify: `jakartaee-microprofile-example/**/*.java`
- Modify: `payara-micro-example/**/*.java`
- Modify: `wildfly-example/**/*.java`
- Modify: `javafx-example/**/*.java`

- [ ] 为 Controller/Resource/Configuration/Service 的职责补充中文注释。
- [ ] 标注框架生命周期、REST 入口、SSE/Streaming 处理点。
- [ ] 解释 AI 调用如何进入普通 Java Web 或桌面应用。

### Task 5: Agent、MCP 与业务示例

**Files:**
- Modify: `agentic-tutorial/**/*.java`
- Modify: `customer-support-agent-example/**/*.java`
- Modify: `azure-open-ai-customer-support-agent-example/**/*.java`
- Modify: `mcp-example/**/*.java`
- Modify: `mcp-github-example/**/*.java`
- Modify: `gpullama3.java-example/**/*.java`
- Modify: `jlama-examples/**/*.java`
- Modify: `native-java-gemini-function-calling-example/**/*.java`
- Modify: `dbpedia-example/**/*.java`

- [ ] 为 Agent 接口、工具类、编排流程补充中文注释。
- [ ] 解释 MCP stdio/HTTP、GitHub 工具、函数调用和知识库查询示例。
- [ ] 对业务示例中的领域对象、工具调用和异常处理补充学习说明。

## 验证

- [ ] 每个 worker 完成后报告修改过的文件列表。
- [ ] 主线程抽查每组至少 3 个文件，确认注释有价值且没有改变代码。
- [ ] 运行 `.\mvnw.cmd -pl tutorials -DskipTests compile` 验证已有教程模块。
- [ ] 可选：运行 `git diff --check` 检查空白问题。
