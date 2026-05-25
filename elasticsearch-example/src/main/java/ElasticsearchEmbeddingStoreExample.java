import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.elasticsearch.ElasticsearchEmbeddingStore;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RestClient;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import java.io.IOException;

public class ElasticsearchEmbeddingStoreExample {

    public static void main(String[] args) throws IOException {

        try (ElasticsearchContainer elastic =
                new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:8.15.0")
                        .withPassword("changeme")
        ) {
            // 使用 Testcontainers 启动带 TLS 的临时 Elasticsearch，密码仅用于本地容器示例。
            elastic.start();

            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "changeme"));

            // RestClient 复用容器生成的 CA 证书，连接到 Testcontainers 暴露的 HTTPS 地址。
            RestClient client = RestClient.builder(HttpHost.create("https://" + elastic.getHttpHostAddress()))
                    .setHttpClientConfigCallback(httpClientBuilder -> {
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                        httpClientBuilder.setSSLContext(elastic.createSslContextFromCa());
                        return httpClientBuilder;
                    })
                    .build();

            // 未指定索引名时使用默认索引，EmbeddingStore 会负责保存向量和 TextSegment。
            EmbeddingStore<TextSegment> embeddingStore = ElasticsearchEmbeddingStore.builder()
                    .restClient(client)
                    .build();

            EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

            // 将文本向量化后写入 Elasticsearch，后续通过向量相似度找回语义最接近的片段。
            TextSegment segment1 = TextSegment.from("I like football.");
            Embedding embedding1 = embeddingModel.embed(segment1).content();
            embeddingStore.add(embedding1, segment1);

            TextSegment segment2 = TextSegment.from("The weather is good today.");
            Embedding embedding2 = embeddingModel.embed(segment2).content();
            embeddingStore.add(embedding2, segment2);

            // 刷新索引让刚写入的向量立即可见；生产环境通常依赖 Elasticsearch 的自动刷新。
            client.performRequest(new Request("POST", "/default/_refresh"));

            // 查询也先转为 embedding，再交给 EmbeddingStore 执行相似度搜索。
            Embedding queryEmbedding = embeddingModel.embed("What is your favourite sport?").content();
            EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(
                    EmbeddingSearchRequest.builder()
                            .queryEmbedding(queryEmbedding)
                            .build());
            EmbeddingMatch<TextSegment> embeddingMatch = relevant.matches().get(0);

            System.out.println(embeddingMatch.score()); // 0.8138435
            System.out.println(embeddingMatch.embedded().text()); // I like football.

            client.close();
        }
    }
}
