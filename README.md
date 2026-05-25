# LangChain4j 示例项目导航

这是一个基于 [LangChain4j](https://github.com/langchain4j/langchain4j) 的多模块示例仓库，覆盖了：

- 不同大模型提供商接入
- RAG 与向量数据库集成
- Agent、工具调用、结构化输出、流式响应
- Spring Boot、Quarkus、Helidon、Jakarta EE、JavaFX 等框架集成
- MCP、GitHub、数据库、搜索引擎等实战示例

社区更多示例可参考：
[langchain4j-community-examples](https://github.com/langchain4j/langchain4j-community-examples)

## 快速开始

建议优先看下面几个目录：

- `tutorials`：入门教程，适合第一次接触 LangChain4j
- `other-examples`：基础能力示例，如 Prompt、Memory、Tools、Retriever、流式输出
- `rag-examples`：RAG 与高级 RAG 技术示例
- `agentic-tutorial`：Agentic workflow / 多 Agent 编排教程
- `customer-support-agent-example`：一个包含记忆、工具和 RAG 的完整 Agent 示例

## 仓库目录总览

### 1. 教程与通用能力

| 目录 | 主要功能 |
| --- | --- |
| `tutorials` | LangChain4j 基础教程，适合从 Hello World 到基础问答逐步上手。 |
| `other-examples` | 通用能力示例集合，覆盖 Prompt Template、Chat Memory、Documents、AiService、Tools、Streaming、Moderation 等。 |
| `agentic-tutorial` | Agentic 编排教程，包含顺序、并行、循环、条件分支、Supervisor、人类介入等工作流。 |
| `customer-support-agent-example` | 完整客服 Agent 示例，整合记忆、工具调用和 RAG。 |
| `azure-open-ai-customer-support-agent-example` | 基于 Azure OpenAI 的客服 Agent 版本，适合参考云上部署场景。 |
| `javafx-example` | JavaFX 图形界面示例，演示桌面端接入 LLM 的方式。 |

### 2. 模型提供商示例

| 目录 | 主要功能 |
| --- | --- |
| `open-ai-examples` | OpenAI 示例，包含聊天、流式响应、Embedding、函数调用、图像、音频、可观测性等。 |
| `azure-open-ai-examples` | Azure OpenAI 基础示例，演示 Azure 环境下的模型接入方式。 |
| `anthropic-examples` | Anthropic Claude 示例，覆盖聊天、流式响应和工具调用。 |
| `google-ai-gemini-examples` | Google AI Gemini 示例，包含聊天、流式、Embedding、JSON 输出、Tools、多模态、批处理等。 |
| `vertex-ai-gemini-examples` | Vertex AI Gemini 示例，适合 GCP Vertex AI 场景。 |
| `native-java-gemini-function-calling-example` | 原生 Java 调用 Gemini function calling 的示例。 |
| `github-models-examples` | GitHub Models 接入示例，演示统一接口调用模型。 |
| `mistral-ai-examples` | Mistral AI 示例。 |
| `ollama-examples` | Ollama 本地模型示例，适合本地运行与离线开发。 |
| `ovh-ai-examples` | OVH AI 模型服务接入示例。 |
| `voyage-ai-examples` | Voyage AI 示例，通常用于向量/Embedding 场景。 |
| `watsonx-ai-examples` | IBM watsonx.ai 示例。 |
| `bedrock-examples` | Amazon Bedrock 示例。 |
| `gpullama3.java-example` | 基于 gpullama3.java 的本地/轻量模型示例。 |
| `jlama-examples` | Jlama 集成示例，包含本地聊天、流式输出、RAG、函数调用。 |

### 3. RAG、向量库与数据库

| 目录 | 主要功能 |
| --- | --- |
| `rag-examples` | RAG 专题示例，包含基础 RAG 以及查询压缩、路由、重排、元数据过滤、多检索器、Web Search、SQL Retriever 等高级技术。 |
| `chroma-example` | Chroma 向量库示例。 |
| `milvus-example` | Milvus 向量数据库示例。 |
| `pgvector-example` | PostgreSQL + pgvector 示例。 |
| `pinecone-example` | Pinecone 向量数据库示例。 |
| `qdrant-example` | Qdrant 向量数据库示例。 |
| `weaviate-example` | Weaviate 向量数据库示例。 |
| `vespa-example` | Vespa 检索/向量搜索示例。 |
| `redis-example` | Redis 向量检索或缓存相关示例。 |
| `opensearch-example` | OpenSearch 检索与向量能力示例。 |
| `elasticsearch-example` | Elasticsearch 检索/向量检索示例。 |
| `neo4j-example` | Neo4j 图数据库示例，可用于图谱增强检索等场景。 |
| `oracle-example` | Oracle 数据库相关示例。 |
| `google-alloydb-example` | Google AlloyDB 集成示例。 |
| `yugabytedb-example` | YugabyteDB 集成示例。 |
| `couchbase-example` | Couchbase 集成示例。 |
| `infinispan-example` | Infinispan 集成示例。 |
| `jvector-example` | JVector 向量检索示例。 |
| `s3-vectors-example` | 基于 S3 Vectors 的向量存储/检索示例。 |
| `dbpedia-example` | DBpedia 知识库相关示例。 |

### 4. MCP、Agent 工具与外部系统

| 目录 | 主要功能 |
| --- | --- |
| `mcp-example` | MCP 基础示例，演示与 Model Context Protocol 生态集成。 |
| `mcp-github-example` | MCP + GitHub 示例，展示通过 MCP 调用 GitHub 能力。 |

### 5. Java 框架与运行时集成

| 目录 | 主要功能 |
| --- | --- |
| `spring-boot-example` | Spring Boot 集成示例。 |
| `spring-boot4-example` | Spring Boot 4 集成示例。 |
| `quarkus-example` | Quarkus 集成示例。 |
| `helidon-examples` | Helidon 示例集合，目前包含 `coffee-shop-assistant-se` 与 `coffee-shop-assistant-mp` 两个咖啡店助手示例。 |
| `jakartaee-microprofile-example` | Jakarta EE / MicroProfile 集成示例。 |
| `payara-micro-example` | Payara Micro 集成示例。 |
| `wildfly-example` | WildFly 集成示例。 |

## 模块清单说明

下面是当前项目中主要目录的一句话说明，方便快速定位：

| 模块 | 说明 |
| --- | --- |
| `agentic-tutorial` | 多 Agent / Agentic workflow 教程，适合学习编排模式。 |
| `anthropic-examples` | Anthropic Claude 接入示例。 |
| `azure-open-ai-customer-support-agent-example` | Azure OpenAI 版客服 Agent 示例。 |
| `azure-open-ai-examples` | Azure OpenAI 通用示例。 |
| `bedrock-examples` | Amazon Bedrock 模型接入示例。 |
| `chroma-example` | Chroma 向量库示例。 |
| `couchbase-example` | Couchbase 集成示例。 |
| `customer-support-agent-example` | 带记忆、工具与 RAG 的客服 Agent 实战示例。 |
| `dbpedia-example` | DBpedia 知识库示例。 |
| `elasticsearch-example` | Elasticsearch 检索/向量检索示例。 |
| `github-models-examples` | GitHub Models 接入示例。 |
| `google-ai-gemini-examples` | Google AI Gemini 全面示例。 |
| `google-alloydb-example` | AlloyDB 集成示例。 |
| `gpullama3.java-example` | gpullama3.java 本地模型示例。 |
| `helidon-examples` | Helidon 下的咖啡店助手示例集合。 |
| `infinispan-example` | Infinispan 集成示例。 |
| `jakartaee-microprofile-example` | Jakarta EE / MicroProfile 集成示例。 |
| `javafx-example` | JavaFX 桌面应用示例。 |
| `jlama-examples` | Jlama 本地推理集成示例。 |
| `jvector-example` | JVector 向量检索示例。 |
| `mcp-example` | MCP 基础示例。 |
| `mcp-github-example` | MCP 与 GitHub 联动示例。 |
| `milvus-example` | Milvus 向量数据库示例。 |
| `mistral-ai-examples` | Mistral AI 示例。 |
| `native-java-gemini-function-calling-example` | 原生 Java Gemini function calling 示例。 |
| `neo4j-example` | Neo4j 图数据库示例。 |
| `ollama-examples` | Ollama 本地模型示例。 |
| `open-ai-examples` | OpenAI 聊天、Embedding、函数调用、图像、音频等示例。 |
| `opensearch-example` | OpenSearch 示例。 |
| `oracle-example` | Oracle 数据库集成示例。 |
| `other-examples` | LangChain4j 常用基础能力示例合集。 |
| `ovh-ai-examples` | OVH AI 示例。 |
| `payara-micro-example` | Payara Micro 示例。 |
| `pgvector-example` | pgvector 示例。 |
| `pinecone-example` | Pinecone 示例。 |
| `qdrant-example` | Qdrant 示例。 |
| `quarkus-example` | Quarkus 集成示例。 |
| `rag-examples` | 基础与高级 RAG 示例合集。 |
| `redis-example` | Redis 集成示例。 |
| `s3-vectors-example` | S3 Vectors 示例。 |
| `spring-boot-example` | Spring Boot 集成示例。 |
| `spring-boot4-example` | Spring Boot 4 集成示例。 |
| `tutorials` | LangChain4j 入门教程。 |
| `vertex-ai-gemini-examples` | Vertex AI Gemini 示例。 |
| `vespa-example` | Vespa 检索示例。 |
| `voyage-ai-examples` | Voyage AI 示例。 |
| `watsonx-ai-examples` | IBM watsonx.ai 示例。 |
| `weaviate-example` | Weaviate 示例。 |
| `wildfly-example` | WildFly 集成示例。 |
| `yugabytedb-example` | YugabyteDB 集成示例。 |

## 运行方式

这是一个 Maven 多模块项目，通常可以直接在 IDE 中运行各模块下的 `main` 方法。

如果希望先整体构建，可以在仓库根目录执行：

```bash
mvn clean package
```

某些模块需要单独配置环境变量或 API Key，例如：

- `OPENAI_API_KEY`
- `GOOGLE_AI_GEMINI_API_KEY`
- `ANTHROPIC_API_KEY`
- Azure / AWS / GCP 对应凭据

建议进入具体模块目录，优先阅读该模块下的 `README.md`、`pom.xml` 和 `src/main/java` 中的示例类。

## 建议阅读顺序

如果你是第一次看这个仓库，推荐按下面顺序浏览：

1. `tutorials`
2. `other-examples`
3. `open-ai-examples` 或你正在使用的模型提供商目录
4. `rag-examples`
5. `agentic-tutorial`
6. `customer-support-agent-example`

## 问题反馈

如果你在使用过程中遇到问题或想提需求，可以到官方仓库提交 Issue：
[langchain4j issues](https://github.com/langchain4j/langchain4j/issues/new/choose)
