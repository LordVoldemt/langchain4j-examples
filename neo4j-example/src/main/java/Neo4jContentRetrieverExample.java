import dev.langchain4j.community.rag.content.retriever.neo4j.Neo4jGraph;
import dev.langchain4j.community.rag.content.retriever.neo4j.Neo4jText2CypherRetriever;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.query.Query;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.testcontainers.containers.Neo4jContainer;

import java.util.List;

import static dev.langchain4j.internal.Utils.getOrDefault;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

public class Neo4jContentRetrieverExample {
    // You can use "demo" api key for demonstration purposes.
    // You can get your own OpenAI API key here: https://platform.openai.com/account/api-keys
    public static final String OPENAI_API_KEY = getOrDefault(System.getenv("OPENAI_API_KEY"), "demo");
    public static final String OPENAI_BASE_URL = "demo".equals(OPENAI_API_KEY) ? "http://langchain4j.dev/demo/openai/v1" : null;

    public static void main(String[] args) {
        // Text2Cypher 需要聊天模型把自然语言问题转换为 Cypher，真实使用时请通过环境变量提供 API key。
        final OpenAiChatModel chatLanguageModel = OpenAiChatModel.builder()
                .apiKey(OPENAI_API_KEY)
                .baseUrl(OPENAI_BASE_URL)
                .modelName(GPT_4_O_MINI)
                .build();

        try (Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>("neo4j:5.26")
                // APOC 插件用于增强图数据库能力；这里的 Neo4j 由 Testcontainers 临时启动。
                .withoutAuthentication()
                .withLabsPlugins("apoc")) {
            neo4jContainer.start();
            try (Driver driver = GraphDatabase.driver(neo4jContainer.getBoltUrl(), AuthTokens.none())) {
                try (Neo4jGraph graph = Neo4jGraph.builder().driver(driver).build()) {
                    try (Session session = driver.session()) {
                        // 写入一小段图数据，供后面的自然语言查询转换成 Cypher 后检索。
                        session.run("CREATE (book:Book {title: 'Dune'})<-[:WROTE]-(author:Person {name: 'Frank Herbert'})");
                    }
                    // Neo4jGraph 创建后又执行了写入，所以需要刷新 schema；如果外部已提前建好图数据则不需要。
                    graph.refreshSchema();

                    // Retriever 会结合图 schema 和 chat model，把 Query 翻译成 Cypher 并返回图查询结果。
                    Neo4jText2CypherRetriever retriever = Neo4jText2CypherRetriever.builder()
                            .graph(graph)
                            .chatModel(chatLanguageModel)
                            .build();

                    Query query = new Query("Who is the author of the book 'Dune'?");

                    List<Content> contents = retriever.retrieve(query);

                    System.out.println(contents.get(0).textSegment().text()); // "Frank Herbert"
                }
            }
        }
    }
}
