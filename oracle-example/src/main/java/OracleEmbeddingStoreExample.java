import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2q.AllMiniLmL6V2QuantizedEmbeddingModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.oracle.CreateOption;
import dev.langchain4j.store.embedding.oracle.OracleEmbeddingStore;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.testcontainers.oracle.OracleContainer;

import java.sql.SQLException;

public class OracleEmbeddingStoreExample {

    public static void main(String[] args) throws SQLException {

        PoolDataSource dataSource = PoolDataSourceFactory.getPoolDataSource();
        dataSource.setConnectionFactoryClassName(
                "oracle.jdbc.datasource.impl.OracleDataSource");
        String urlFromEnv = System.getenv("ORACLE_JDBC_URL");

        if (urlFromEnv == null) {
            // 未提供外部 Oracle 连接时，使用 Testcontainers 启动本地临时 Oracle 数据库。
            OracleContainer oracleContainer = new OracleContainer(
                    "gvenzl/oracle-free:23.4-slim-faststart")
                    .withDatabaseName("pdb1")
                    .withUsername("testuser")
                    .withPassword("testpwd");
            oracleContainer.start();
            dataSource.setURL(oracleContainer.getJdbcUrl());
            dataSource.setUser(oracleContainer.getUsername());
            dataSource.setPassword(oracleContainer.getPassword());

        } else {
            // 外部数据库连接信息来自环境变量，避免在示例代码中写入真实账号密码。
            dataSource.setURL(urlFromEnv);
            dataSource.setUser(System.getenv("ORACLE_JDBC_USER"));
            dataSource.setPassword(System.getenv("ORACLE_JDBC_PASSWORD"));
        }

        // embeddingTable 指定保存向量和文本的表；CREATE_OR_REPLACE 便于重复运行示例。
        EmbeddingStore<TextSegment> embeddingStore = OracleEmbeddingStore.builder()
                .dataSource(dataSource)
                .embeddingTable("test_content_retriever",
                        CreateOption.CREATE_OR_REPLACE)
                .build();

        EmbeddingModel embeddingModel = new AllMiniLmL6V2QuantizedEmbeddingModel();

        // ContentRetriever 封装了“查询向量化 + 向量相似度搜索 + 分数过滤”的 RAG 检索流程。
        ContentRetriever retriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(2)
                .minScore(0.5)
                .build();

        // 写入两段文本及其 embedding 到 Oracle 向量表。
        TextSegment segment1 = TextSegment.from("I like soccer.");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        embeddingStore.add(embedding1, segment1);

        TextSegment segment2 = TextSegment.from("I love Stephen King.");
        Embedding embedding2 = embeddingModel.embed(segment2).content();
        embeddingStore.add(embedding2, segment2);

        // 自然语言问题会被模型转换为 query embedding，然后在 Oracle 表中检索相似内容。
        Content match = retriever
                .retrieve(Query.from("What is your favourite sport?"))
                .get(0);

        System.out.println(match.textSegment());
    }
}
