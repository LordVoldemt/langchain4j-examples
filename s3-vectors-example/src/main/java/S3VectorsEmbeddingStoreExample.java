import dev.langchain4j.community.store.embedding.s3.S3VectorsEmbeddingStore;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import java.util.List;

/**
 * 前置条件：
 * 1. 配置 AWS 凭据（例如 AWS_ACCESS_KEY_ID/AWS_SECRET_ACCESS_KEY 环境变量、
 *    ~/.aws/credentials 文件或 IAM role），不要在代码中写入真实密钥。
 * 2. 创建 S3 Vector Bucket：
 *    aws s3vectors create-vector-bucket --vector-bucket-name my-vector-bucket
 * 3. 将 S3_VECTORS_BUCKET_NAME 环境变量设置为 bucket 名称。
 * 4. 可选设置 AWS_REGION，默认使用 "us-east-1"。
 */
public class S3VectorsEmbeddingStoreExample {

    public static void main(String[] args) {

        String bucketName = System.getenv("S3_VECTORS_BUCKET_NAME");
        String region = System.getenv().getOrDefault("AWS_REGION", "us-east-1");

        // indexName 指定 S3 Vectors 中的向量索引，createIndexIfNotExists 便于首次运行示例。
        try (S3VectorsEmbeddingStore embeddingStore = S3VectorsEmbeddingStore.builder()
                .vectorBucketName(bucketName)
                .indexName("s3-vectors-example-index")
                .region(region)
                .createIndexIfNotExists(true)
                .build()) {

            EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

            // 将文本转换为 embedding 后写入 S3 Vectors index。
            TextSegment segment1 = TextSegment.from("I like football.");
            Embedding embedding1 = embeddingModel.embed(segment1).content();
            embeddingStore.add(embedding1, segment1);

            TextSegment segment2 = TextSegment.from("The weather is good today.");
            Embedding embedding2 = embeddingModel.embed(segment2).content();
            embeddingStore.add(embedding2, segment2);

            // 查询文本同样先向量化，再在 S3 Vectors 中做相似度搜索。
            Embedding queryEmbedding = embeddingModel.embed("What is your favourite sport?").content();
            EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                    .queryEmbedding(queryEmbedding)
                    .maxResults(1)
                    .build();
            List<EmbeddingMatch<TextSegment>> matches = embeddingStore.search(embeddingSearchRequest).matches();
            EmbeddingMatch<TextSegment> embeddingMatch = matches.get(0);

            System.out.println(embeddingMatch.score()); // 0.8144288493114709
            System.out.println(embeddingMatch.embedded().text()); // I like football.
        }
    }
}
