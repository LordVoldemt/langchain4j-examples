import static dev.langchain4j.internal.Utils.randomUUID;
import static dev.langchain4j.store.embedding.chroma.ChromaApiVersion.V2;
import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.chroma.ChromaEmbeddingStore;
import dev.langchain4j.store.embedding.filter.Filter;
import org.testcontainers.chromadb.ChromaDBContainer;

public class ChromaEmbeddingStoreWithMetadataExample {

    public static void main(String[] args) {
        try (ChromaDBContainer chroma = new ChromaDBContainer("chromadb/chroma:1.1.0").withExposedPorts(8000)) {
            // 使用 Testcontainers 启动临时 Chroma，便于本地直接运行向量检索示例。
            chroma.start();

            // 每次运行创建独立 collection，避免 metadata 过滤受到历史数据影响。
            EmbeddingStore<TextSegment> embeddingStore = ChromaEmbeddingStore.builder()
                .apiVersion(V2)
                .baseUrl(chroma.getEndpoint())
                .collectionName(randomUUID())
                .logRequests(true)
                .logResponses(true)
                .build();

            EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

            // 写入文本时携带 userId metadata，后续检索可在相似度搜索前先按 metadata 缩小范围。
            TextSegment segment1 = TextSegment.from("I like football.", Metadata.metadata("userId", "1"));
            Embedding embedding1 = embeddingModel.embed(segment1).content();
            embeddingStore.add(embedding1, segment1);

            TextSegment segment2 = TextSegment.from("I like basketball.", Metadata.metadata("userId", "2"));
            Embedding embedding2 = embeddingModel.embed(segment2).content();
            embeddingStore.add(embedding2, segment2);

            Embedding queryEmbedding = embeddingModel.embed("What is your favourite sport?").content();

            // 只搜索 userId=1 的向量；相似度排序只在过滤后的候选集合中进行。

            Filter onlyForUser1 = metadataKey("userId").isEqualTo("1");

            EmbeddingSearchRequest embeddingSearchRequest1 = EmbeddingSearchRequest
                .builder()
                .queryEmbedding(queryEmbedding)
                .filter(onlyForUser1)
                .build();

            EmbeddingSearchResult<TextSegment> embeddingSearchResult1 = embeddingStore.search(embeddingSearchRequest1);
            EmbeddingMatch<TextSegment> embeddingMatch1 = embeddingSearchResult1.matches().get(0);

            System.out.println(embeddingMatch1.score());
            System.out.println(embeddingMatch1.embedded().text());

            // 同一个查询向量也可以配合不同 metadata 条件，得到不同用户隔离后的结果。

            Filter onlyForUser2 = metadataKey("userId").isEqualTo("2");

            EmbeddingSearchRequest embeddingSearchRequest2 = EmbeddingSearchRequest
                .builder()
                .queryEmbedding(queryEmbedding)
                .filter(onlyForUser2)
                .build();

            EmbeddingSearchResult<TextSegment> embeddingSearchResult2 = embeddingStore.search(embeddingSearchRequest2);
            EmbeddingMatch<TextSegment> embeddingMatch2 = embeddingSearchResult2.matches().get(0);

            System.out.println(embeddingMatch2.score());
            System.out.println(embeddingMatch2.embedded().text());

            chroma.stop();
        }
    }
}
