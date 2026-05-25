import static dev.langchain4j.internal.Utils.randomUUID;
import static dev.langchain4j.store.embedding.chroma.ChromaApiVersion.V2;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.chroma.ChromaEmbeddingStore;
import java.util.List;
import org.testcontainers.chromadb.ChromaDBContainer;

public class ChromaEmbeddingStoreExample {

    public static void main(String[] args) {
        try (ChromaDBContainer chroma = new ChromaDBContainer("chromadb/chroma:1.1.0").withExposedPorts(8000)) {
            // 使用 Testcontainers 启动临时 Chroma 服务，示例结束后容器会随 try-with-resources 释放。
            chroma.start();

            // collectionName 使用随机值，避免多次运行示例时复用旧集合中的向量数据。
            EmbeddingStore<TextSegment> embeddingStore = ChromaEmbeddingStore.builder()
                .apiVersion(V2)
                .baseUrl(chroma.getEndpoint())
                .collectionName(randomUUID())
                .logRequests(true)
                .logResponses(true)
                .build();

            EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

            // 将原始文本转换成 embedding 后写入 Chroma；TextSegment 会作为命中结果中的原文返回。
            TextSegment segment1 = TextSegment.from("I like football.");
            Embedding embedding1 = embeddingModel.embed(segment1).content();
            embeddingStore.add(embedding1, segment1);

            TextSegment segment2 = TextSegment.from("The weather is good today.");
            Embedding embedding2 = embeddingModel.embed(segment2).content();
            embeddingStore.add(embedding2, segment2);

            // 查询文本同样先向量化，再按向量相似度检索最相关的一条记录。
            Embedding queryEmbedding = embeddingModel.embed("What is your favourite sport?").content();
            EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                    .queryEmbedding(queryEmbedding)
                    .maxResults(1)
                    .build();
            List<EmbeddingMatch<TextSegment>> matches = embeddingStore.search(embeddingSearchRequest).matches();
            EmbeddingMatch<TextSegment> embeddingMatch = matches.get(0);

            System.out.println(embeddingMatch.score()); // 0.8144288493114709
            System.out.println(embeddingMatch.embedded().text()); // I like football.

            chroma.stop();
        }
    }
}
