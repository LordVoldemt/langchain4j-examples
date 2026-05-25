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

/**
 * 本示例演示使用 YugabyteDB Smart Driver 连接向量表。
 *
 * YugabyteDB Smart Driver 适合：
 * - 高级分布式数据库特性
 * - 感知拓扑的负载均衡
 * - 节点级连接管理
 * - 多地域部署
 */
public class YugabyteDBWithSmartDriverExample {

    public static void main(String[] args) {
        GenericContainer<?> yugabyteContainer = null;
        YugabyteDBEngine engine = null;

        try {
            DockerImageName dockerImageName = DockerImageName.parse("yugabytedb/yugabyte:2025.1.0.1-b3");
            // 使用 Testcontainers 启动临时 YugabyteDB，示例通过 5433 端口连接。
            yugabyteContainer = new GenericContainer<>(dockerImageName)
                    .withExposedPorts(5433, 7000, 9000, 15433, 9042)
                    .withCommand("bin/yugabyted", "start", "--background=false")
                    .waitingFor(Wait.forListeningPorts(5433).withStartupTimeout(Duration.ofMinutes(5)));
            
            yugabyteContainer.start();

            EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

            System.out.println("=== Using YugabyteDB Smart Driver ===");
            System.out.println("Driver: com.yugabyte.Driver");
            System.out.println("Best for: Distributed deployments with topology-aware load balancing");
            System.out.println();

            // usePostgreSQLDriver(false) 表示使用 YugabyteDB Smart Driver，适合分布式部署场景。
            engine = YugabyteDBEngine.builder()
                    .host(yugabyteContainer.getHost())
                    .port(yugabyteContainer.getMappedPort(5433))
                    .database("yugabyte")
                    .username("yugabyte")
                    .password("yugabyte")
                    .usePostgreSQLDriver(false) // ← Use YugabyteDB Smart Driver (default)
                    .maxPoolSize(10)
                    .build();

            // 创建或复用向量表，表中保存 embedding、文本和内部标识。
            EmbeddingStore<TextSegment> embeddingStore = YugabyteDBEmbeddingStore.builder()
                    .engine(engine)
                    .tableName("smart_driver_embeddings")
                    .dimension(embeddingModel.dimension())
                    .createTableIfNotExists(true)
                    .build();

            // 写入几条描述 Smart Driver 的示例文本及其 embedding。
            TextSegment segment1 = TextSegment.from("Smart Driver provides topology-aware load balancing.");
            Embedding embedding1 = embeddingModel.embed(segment1).content();
            embeddingStore.add(embedding1, segment1);

            TextSegment segment2 = TextSegment.from("Distributed databases benefit from cluster-aware drivers.");
            Embedding embedding2 = embeddingModel.embed(segment2).content();
            embeddingStore.add(embedding2, segment2);

            TextSegment segment3 = TextSegment.from("Multi-region deployments require smart connection management.");
            Embedding embedding3 = embeddingModel.embed(segment3).content();
            embeddingStore.add(embedding3, segment3);

            // 查询向量用于查找与“分布式数据库连接”语义最接近的文本。
            Embedding queryEmbedding = embeddingModel.embed("How do distributed databases handle connections?").content();

            EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                    .queryEmbedding(queryEmbedding)
                    .maxResults(2)
                    .build();

            List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.search(searchRequest).matches();

            System.out.println("Search Results:");
            for (EmbeddingMatch<TextSegment> match : relevant) {
                System.out.println("  Score: " + String.format("%.4f", match.score()));
                System.out.println("  Text: " + match.embedded().text());
                System.out.println();
            }

            System.out.println("\nSmart Driver Features:");
            System.out.println("  ✓ Topology-aware load balancing");
            System.out.println("  ✓ Automatic failover");
            System.out.println("  ✓ Connection pooling per node");
            System.out.println("  ✓ Preferred region support");

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
