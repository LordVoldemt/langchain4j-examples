import dev.langchain4j.community.store.embedding.yugabytedb.YugabyteDBEmbeddingStore;
import dev.langchain4j.community.store.embedding.yugabytedb.YugabyteDBEngine;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.List;

public class YugabyteDBEmbeddingStoreExample {

    public static void main(String[] args) {
        GenericContainer<?> yugabyteContainer = null;
        YugabyteDBEngine engine = null;

        try {
            DockerImageName dockerImageName = DockerImageName.parse("yugabytedb/yugabyte:2025.1.0.1-b3");
            // 使用 Testcontainers 启动单节点 YugabyteDB，暴露 PostgreSQL 兼容端口 5433。
            yugabyteContainer = new GenericContainer<>(dockerImageName)
                    .withExposedPorts(5433, 7000, 9000, 15433, 9042)
                    .withCommand("bin/yugabyted", "start", "--background=false")
                    .waitingFor(Wait.forListeningPorts(5433).withStartupTimeout(Duration.ofMinutes(5)));
            
            yugabyteContainer.start();

            EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

            // 使用 PostgreSQL JDBC driver 连接 YugabyteDB，连接信息来自临时容器。
            engine = YugabyteDBEngine.builder()
                    .host(yugabyteContainer.getHost())
                    .port(yugabyteContainer.getMappedPort(5433))
                    .database("yugabyte")
                    .username("yugabyte")
                    .password("yugabyte")
                    .usePostgreSQLDriver(true) // Use PostgreSQL JDBC driver
                    .build();

            // tableName 指定向量表，createTableIfNotExists 便于首次运行时自动建表。
            EmbeddingStore<TextSegment> embeddingStore = YugabyteDBEmbeddingStore.builder()
                    .engine(engine)
                    .tableName("test_embeddings")
                    .dimension(embeddingModel.dimension())
                    .createTableIfNotExists(true)
                    .build();

            // 写入文本和 embedding 到 YugabyteDB 向量表。
            TextSegment segment1 = TextSegment.from("I like football.");
            Embedding embedding1 = embeddingModel.embed(segment1).content();
            embeddingStore.add(embedding1, segment1);

            TextSegment segment2 = TextSegment.from("The weather is good today.");
            Embedding embedding2 = embeddingModel.embed(segment2).content();
            embeddingStore.add(embedding2, segment2);

            // 查询文本向量化后，使用数据库中的向量索引/相似度能力找回相关文本。
            Embedding queryEmbedding = embeddingModel.embed("What is your favourite sport?").content();

            EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                    .queryEmbedding(queryEmbedding)
                    .maxResults(1)
                    .build();

            List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.search(embeddingSearchRequest).matches();

            EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);

            System.out.println(embeddingMatch.score()); // ~0.8144
            System.out.println(embeddingMatch.embedded().text()); // I like football.

            System.out.println("\n✅ Example completed successfully!");

        } catch (Exception e) {
            System.err.println("❌ Error running example: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 给 Testcontainers 一点时间做容器清理，避免本地资源释放过快导致日志不完整。
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
            
            // 关闭数据库连接池并停止容器。
            System.out.println("🧹 Cleaning up resources...");
            if (engine != null) {
                try {
                    engine.close();
                } catch (Exception e) {
                    System.err.println("Error closing engine: " + e.getMessage());
                }
            }
            if (yugabyteContainer != null) {
                try {
                    yugabyteContainer.stop();
                } catch (Exception e) {
                    System.err.println("Error stopping container: " + e.getMessage());
                }
            }
        }
    }
}

