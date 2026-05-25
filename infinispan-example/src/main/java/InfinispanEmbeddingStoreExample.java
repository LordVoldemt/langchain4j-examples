import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.infinispan.InfinispanEmbeddingStore;
import org.infinispan.client.hotrod.configuration.ClientIntelligence;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.server.test.core.InfinispanContainer;

import java.util.List;

import static org.infinispan.server.test.core.InfinispanContainer.DEFAULT_PASSWORD;
import static org.infinispan.server.test.core.InfinispanContainer.DEFAULT_USERNAME;

public class InfinispanEmbeddingStoreExample {

    public static void main(String[] args) {

        InfinispanContainer infinispan = new InfinispanContainer();
        // Testcontainers 启动临时 Infinispan Server，并通过 Hot Rod 客户端连接。
        infinispan.start();

        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addServer().host(infinispan.getHost())
                .port(infinispan.getFirstMappedPort())
                .security()
                .authentication()
                .username(DEFAULT_USERNAME)
                .password(DEFAULT_PASSWORD);
        // 仅用于规避 Docker for Mac 网络问题，生产环境不要使用 BASIC client intelligence。
        builder.clientIntelligence(ClientIntelligence.BASIC);

        // cacheName 指定保存向量和文本的缓存，dimension 必须与 embedding 模型输出维度一致。
        EmbeddingStore<TextSegment> embeddingStore = InfinispanEmbeddingStore.builder()
                .cacheName("my-cache")
                .dimension(384)
                .infinispanConfigBuilder(builder)
                .build();

        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

        // 文本先转换为 embedding，再写入 Infinispan 的向量缓存。
        TextSegment segment1 = TextSegment.from("I like football.");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        embeddingStore.add(embedding1, segment1);

        TextSegment segment2 = TextSegment.from("The weather is good today.");
        Embedding embedding2 = embeddingModel.embed(segment2).content();
        embeddingStore.add(embedding2, segment2);

        // 查询向量与缓存中的向量做相似度匹配，返回最相关的一条文本。
        Embedding queryEmbedding = embeddingModel.embed("What is your favourite sport?").content();
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1)
                .build();
        List<EmbeddingMatch<TextSegment>> matches = embeddingStore.search(embeddingSearchRequest).matches();
        EmbeddingMatch<TextSegment> embeddingMatch = matches.get(0);

        System.out.println(embeddingMatch.score()); // 0.8144288659095
        System.out.println(embeddingMatch.embedded().text()); // I like football.

        infinispan.stop();
    }
}
