import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.weaviate.WeaviateEmbeddingStore;
import org.testcontainers.weaviate.WeaviateContainer;

import java.util.List;

public class WeaviateEmbeddingStoreExample {

    public static void main(String[] args) {

        try (WeaviateContainer weaviate = new WeaviateContainer("semitechnologies/weaviate:1.22.4")) {
            // Testcontainers 启动临时 Weaviate，示例通过 HTTP 地址连接。
            weaviate.start();
            // objectClass 对应 Weaviate class，用于保存向量和文本对象。
            EmbeddingStore<TextSegment> embeddingStore = WeaviateEmbeddingStore.builder()
                    .scheme("http")
                    .host(weaviate.getHttpHostAddress())
                    // 未指定时使用 "Default" class；Weaviate class 名必须以大写字母开头。
                    .objectClass("Test")
                    // true 时根据文本生成稳定哈希 ID，避免同一文本重复入库；false 则生成随机 ID。
                    .avoidDups(true)
                    // 一致性级别可选 ONE、QUORUM（默认）或 ALL。
                    .consistencyLevel("ALL")
                    .build();

            EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

            // 写入文本和 embedding 到 Weaviate class。
            TextSegment segment1 = TextSegment.from("I like football.");
            Embedding embedding1 = embeddingModel.embed(segment1).content();
            embeddingStore.add(embedding1, segment1);

            TextSegment segment2 = TextSegment.from("The weather is good today.");
            Embedding embedding2 = embeddingModel.embed(segment2).content();
            embeddingStore.add(embedding2, segment2);

            // 查询向量在 Weaviate 中检索最相似对象，并返回保存的原文。
            Embedding queryEmbedding = embeddingModel.embed("What is your favourite sport?").content();
            EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                    .queryEmbedding(queryEmbedding)
                    .maxResults(1)
                    .build();
            List<EmbeddingMatch<TextSegment>> matches = embeddingStore.search(embeddingSearchRequest).matches();
            EmbeddingMatch<TextSegment> embeddingMatch = matches.get(0);

            System.out.println(embeddingMatch.score()); // 0.8144288063049316
            System.out.println(embeddingMatch.embedded().text()); // I like football.
        }
    }
}
