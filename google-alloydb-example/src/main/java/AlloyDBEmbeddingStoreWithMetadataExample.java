import dev.langchain4j.community.store.embedding.alloydb.AlloyDBEmbeddingStore;
import dev.langchain4j.community.store.embedding.alloydb.AlloyDBEngine;
import dev.langchain4j.community.store.embedding.alloydb.EmbeddingStoreConfig;
import dev.langchain4j.community.store.embedding.alloydb.MetadataColumn;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.filter.Filter;

import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AlloyDBEmbeddingStoreWithMetadataExample {

        private static final String TABLE_NAME = "EMBEDDING_TEST_TABLE";

        public static void main(String[] args) {

                EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
                // 从环境变量读取 AlloyDB 连接信息；示例不内置真实账号或密码。
                AlloyDBEngine engine = new AlloyDBEngine.Builder()
                                .projectId(System.getenv("ALLOYDB_PROJECT_ID"))
                                .region(System.getenv("ALLOYDB_REGION"))
                                .cluster(System.getenv("ALLOYDB_CLUSTER"))
                                .instance(System.getenv("ALLOYDB_INSTANCE"))
                                .database(System.getenv("ALLOYDB_DB_NAME"))
                                .user(System.getenv("ALLOYDB_USER"))
                                .password(System.getenv("ALLOYDB_PASSWORD")).ipType("public")
                                .build();

                // 创建向量表时声明 userId metadata 列，便于数据库侧执行过滤。
                List<MetadataColumn> metadataColumns = new ArrayList<>();
                metadataColumns.add(new MetadataColumn("userId", "uuid", true));
                EmbeddingStoreConfig embeddingStoreConfig =
                                new EmbeddingStoreConfig.Builder(TABLE_NAME, embeddingModel.dimension())
                                                .metadataColumns(metadataColumns)
                                                .overwriteExisting(true).storeMetadata(true)
                                                .build();

                engine.initVectorStoreTable(embeddingStoreConfig);

                // EmbeddingStore 绑定到刚创建的表，并告知哪些 metadata 字段需要映射为表列。
                List<String> metaColumnNames = metadataColumns.stream().map(MetadataColumn::getName)
                                .collect(Collectors.toList());

                AlloyDBEmbeddingStore embeddingStore =
                                new AlloyDBEmbeddingStore.Builder(engine, TABLE_NAME)
                                                .metadataColumns(metaColumnNames).build();

                // 写入文本、embedding 和 userId；后续可用 userId 过滤多租户或用户级数据。
                Metadata metadata1 = new Metadata();
                UUID user1 = UUID.randomUUID();
                metadata1.put("userId", user1);
                TextSegment segment1 = TextSegment.from("I like turtles.", metadata1);
                Embedding embedding1 = embeddingModel.embed(segment1).content();
                embeddingStore.add(embedding1, segment1);

                Metadata metadata2 = new Metadata();
                UUID user2 = UUID.randomUUID();
                metadata2.put("userId", user2);
                TextSegment segment2 = TextSegment.from(
                                "I do not like fish. My favorite animal is a horse!", metadata2);
                Embedding embedding2 = embeddingModel.embed(segment2).content();
                embeddingStore.add(embedding2, segment2);

                // 不带过滤条件时，会在整张向量表中按语义相似度检索。
                Embedding queryEmbedding =
                                embeddingModel.embed("What is your favorite animal?").content();
                EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                                .queryEmbedding(queryEmbedding).maxResults(1).build();
                EmbeddingSearchResult<TextSegment> searchResult =
                                embeddingStore.search(searchRequest);

                EmbeddingMatch<TextSegment> embeddingMatch = searchResult.matches().get(0);
                System.out.println("Unfiltered match:");
                System.out.println(embeddingMatch.score());
                System.out.println(embeddingMatch.embedded().text());

                // 带 metadata 过滤时，只有 userId 匹配的行会参与向量相似度搜索。
                Filter onlyForUser1 = metadataKey("userId").isEqualTo(user1);

                EmbeddingSearchRequest embeddingSearchRequest1 = EmbeddingSearchRequest.builder()
                                .queryEmbedding(queryEmbedding).filter(onlyForUser1).build();

                EmbeddingSearchResult<TextSegment> embeddingSearchResult1 =
                                embeddingStore.search(embeddingSearchRequest1);
                EmbeddingMatch<TextSegment> embeddingMatch1 =
                                embeddingSearchResult1.matches().get(0);
                System.out.println("Filtered match:");
                System.out.println(embeddingMatch1.score());
                System.out.println(embeddingMatch1.embedded().text());

                engine.close();
        }
}
