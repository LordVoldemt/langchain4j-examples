import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.opensearch.OpenSearchEmbeddingStore;
import org.opensearch.testcontainers.OpensearchContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

public class OpenSearchEmbeddingStoreExample {

    public static void main(String[] args) throws InterruptedException {

        try (var opensearch = new OpensearchContainer(DockerImageName.parse("opensearchproject/opensearch:2.0.0"))) {
            // Testcontainers 启动临时 OpenSearch 节点，示例不依赖外部集群。
            opensearch.start();
            // serverUrl 指向容器暴露的 HTTP 地址，EmbeddingStore 负责创建/使用默认向量索引。
            EmbeddingStore<TextSegment> embeddingStore = OpenSearchEmbeddingStore.builder()
                    .serverUrl(opensearch.getHttpHostAddress())
                    .build();

            EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

            // 写入文本及其 embedding，后续可通过向量相似度找回原始 TextSegment。
            TextSegment segment1 = TextSegment.from("I like football.");
            Embedding embedding1 = embeddingModel.embed(segment1).content();
            embeddingStore.add(embedding1, segment1);

            TextSegment segment2 = TextSegment.from("The weather is good today.");
            Embedding embedding2 = embeddingModel.embed(segment2).content();
            embeddingStore.add(embedding2, segment2);

            Thread.sleep(1000); // 等待 OpenSearch 完成写入和索引刷新，确保搜索能看到新向量。

            // 查询文本生成 embedding 后，返回最相近的一条向量记录。
            Embedding queryEmbedding = embeddingModel.embed("What is your favourite sport?").content();
            EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                    .queryEmbedding(queryEmbedding)
                    .maxResults(1)
                    .build();
            List<EmbeddingMatch<TextSegment>> matches = embeddingStore.search(embeddingSearchRequest).matches();
            EmbeddingMatch<TextSegment> embeddingMatch = matches.get(0);

            System.out.println(embeddingMatch.score()); // 0.8144289
            System.out.println(embeddingMatch.embedded().text()); // I like football.
        }
    }
}
