import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.elasticsearch.ElasticsearchConfigurationScript;
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

public class ElasticsearchEmbeddingStoreWithScriptExample {

    public static void main(String[] args) throws IOException {

        try (ElasticsearchContainer elastic =
                new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:8.15.0")
                        .withPassword("changeme")
        ) {
            // 使用 Testcontainers 启动临时 Elasticsearch，避免示例依赖本机预装服务。
            elastic.start();

            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "changeme"));

            RestClient client = RestClient.builder(HttpHost.create("https://" + elastic.getHttpHostAddress()))
                    .setHttpClientConfigCallback(httpClientBuilder -> {
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                        httpClientBuilder.setSSLContext(elastic.createSslContextFromCa());
                        return httpClientBuilder;
                    })
                    .build();

            // 这里演示使用脚本配置方式创建 Elasticsearch 向量索引和检索脚本。
            EmbeddingStore<TextSegment> embeddingStore = ElasticsearchEmbeddingStore.builder()
                    .restClient(client)
                    .configuration(ElasticsearchConfigurationScript.builder().build())
                    .build();

            EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

            // 写入两条文本对应的 embedding，TextSegment 原文会与向量一起存储。
            TextSegment segment1 = TextSegment.from("I like football.");
            Embedding embedding1 = embeddingModel.embed(segment1).content();
            embeddingStore.add(embedding1, segment1);

            TextSegment segment2 = TextSegment.from("The weather is good today.");
            Embedding embedding2 = embeddingModel.embed(segment2).content();
            embeddingStore.add(embedding2, segment2);

            // 刷新默认索引，确保后面的向量搜索能立刻看到示例写入的数据。
            client.performRequest(new Request("POST", "/default/_refresh"));

            // 按查询 embedding 与已存 embedding 的相似度返回最相关片段。
            Embedding queryEmbedding = embeddingModel.embed("What is your favourite sport?").content();
            EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(
                    EmbeddingSearchRequest.builder()
                            .queryEmbedding(queryEmbedding)
                            .build());
            EmbeddingMatch<TextSegment> embeddingMatch = relevant.matches().get(0);

            System.out.println(embeddingMatch.score()); // 0.81442887
            System.out.println(embeddingMatch.embedded().text()); // I like football.

            client.close();
        }
    }
}
