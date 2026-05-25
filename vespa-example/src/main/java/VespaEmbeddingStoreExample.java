import static java.util.Arrays.asList;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.vespa.VespaEmbeddingStore;
import java.util.List;

/**
 * Vespa 集成示例。运行前需要先按 README.md 配置 Vespa 服务端 schema 和访问凭据。
 */
public class VespaEmbeddingStoreExample {

  public static void main(String[] args) {
    // Vespa 示例依赖外部服务，这里的 url/keyPath/certPath 需要替换为自己的服务地址和证书路径。
    EmbeddingStore<TextSegment> embeddingStore = VespaEmbeddingStore
      .builder()
      // server url, e.g. https://alexey-heezer.langchain4j.mytenant346.aws-us-east-1c.dev.z.vespa-app.cloud
      .url("url")
      // local path to the SSL private key file,
      // e.g. /Users/user/.vespa/mytenant346.langchain4j.alexey-heezer/data-plane-private-key.pem
      .keyPath("keyPath")
      // local path to the SSL certificate file,
      // e.g. /Users/user/.vespa/mytenant346.langchain4j.alexey-heezer/data-plane-public-cert.pem
      .certPath("certPath")
      .build();

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    // 单条 add 写入文本和 embedding，Vespa 根据服务端 schema 保存向量字段。
    TextSegment segment1 = TextSegment.from("I like football.");
    Embedding embedding1 = embeddingModel.embed(segment1).content();
    embeddingStore.add(embedding1, segment1);

    TextSegment segment2 = TextSegment.from("I've never been to New York.");
    Embedding embedding2 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding2, segment2);

    TextSegment segment3 = TextSegment.from(
      "But actually we tried our new swimming pool yesterday and it was awesome!"
    );
    Embedding embedding3 = embeddingModel.embed(segment3).content();
    embeddingStore.add(embedding3, segment3);

    // addAll 演示批量写入多条向量记录。
    List<String> ids = embeddingStore.addAll(
      asList(embedding1, embedding2, embedding3),
      asList(segment1, segment2, segment3)
    );

    System.out.println("added/updated records count: " + ids.size()); // 3

    TextSegment segment4 = TextSegment.from(
      "John Lennon was a very cool person."
    );
    Embedding embedding4 = embeddingModel.embed(segment4).content();
    String s4id = embeddingStore.add(embedding4, segment4);

    System.out.println("segment 4 id: " + s4id);

    // sport 查询返回前 2 个相似片段，展示 maxResults 对返回数量的限制。
    Embedding queryEmbedding = embeddingModel.embed(
      "What is your favorite sport?"
    ).content();
    EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
            .queryEmbedding(queryEmbedding)
            .maxResults(2)
            .build();
    List<EmbeddingMatch<TextSegment>> matches = embeddingStore.search(embeddingSearchRequest).matches();

    System.out.println(
      "relevant results count for sport question: " + matches.size()
    ); // 2

    System.out.println(matches.get(0).score()); // 0.639...
    System.out.println(matches.get(0).embedded().text()); // football
    System.out.println(matches.get(1).score()); // 0.232...
    System.out.println(matches.get(1).embedded().text()); // swimming pool

    // music 查询增加 minScore，只保留相似度达到阈值的结果。
    queryEmbedding = embeddingModel.embed("And what about musicians?").content();
    embeddingSearchRequest = EmbeddingSearchRequest.builder()
            .queryEmbedding(queryEmbedding)
            .maxResults(5)
            .minScore(0.3)
            .build();
    matches = embeddingStore.search(embeddingSearchRequest).matches();

    System.out.println(
      "relevant results count for music question: " + matches.size()
    ); // 1

    System.out.println(matches.get(0).score()); // 0.359...
    System.out.println(matches.get(0).embedded().text()); // John Lennon
  }
}
