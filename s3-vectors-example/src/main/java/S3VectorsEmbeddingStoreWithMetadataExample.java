import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

import dev.langchain4j.community.store.embedding.s3.S3VectorsEmbeddingStore;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.filter.Filter;

/**
 * 前置条件：
 * 1. 配置 AWS 凭据（例如 AWS_ACCESS_KEY_ID/AWS_SECRET_ACCESS_KEY 环境变量、
 *    ~/.aws/credentials 文件或 IAM role），不要在代码中写入真实密钥。
 * 2. 创建 S3 Vector Bucket：
 *    aws s3vectors create-vector-bucket --vector-bucket-name my-vector-bucket
 * 3. 将 S3_VECTORS_BUCKET_NAME 环境变量设置为 bucket 名称。
 * 4. 可选设置 AWS_REGION，默认使用 "us-east-1"。
 */
public class S3VectorsEmbeddingStoreWithMetadataExample {

    public static void main(String[] args) {

        String bucketName = System.getenv("S3_VECTORS_BUCKET_NAME");
        String region = System.getenv().getOrDefault("AWS_REGION", "us-east-1");

        // indexName 指向包含 metadata 的 S3 Vectors 索引，首次运行时可自动创建。
        try (S3VectorsEmbeddingStore embeddingStore = S3VectorsEmbeddingStore.builder()
                .vectorBucketName(bucketName)
                .indexName("s3-vectors-metadata-example-index")
                .region(region)
                .createIndexIfNotExists(true)
                .build()) {

            EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

            // 写入时附带 userId metadata，后续可在向量搜索时做服务端过滤。
            TextSegment segment1 = TextSegment.from("I like football.", Metadata.metadata("userId", "1"));
            Embedding embedding1 = embeddingModel.embed(segment1).content();
            embeddingStore.add(embedding1, segment1);

            TextSegment segment2 = TextSegment.from("I like basketball.", Metadata.metadata("userId", "2"));
            Embedding embedding2 = embeddingModel.embed(segment2).content();
            embeddingStore.add(embedding2, segment2);

            Embedding queryEmbedding = embeddingModel.embed("What is your favourite sport?").content();

            // 只搜索 userId=1 的向量，避免不同用户数据互相命中。

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

            // 更换 metadata 过滤条件即可复用同一个查询向量搜索另一个用户的数据。

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
        }
    }
}
