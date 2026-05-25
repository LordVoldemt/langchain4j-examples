import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.output.Response;

import static dev.langchain4j.model.openai.OpenAiEmbeddingModelName.TEXT_EMBEDDING_3_SMALL;

public class OpenAiEmbeddingModelExamples {

    public static void main(String[] args) {

        // EmbeddingModel 将文本转换为向量；modelName 要选择 embedding 专用模型，而不是 chat 模型。
        EmbeddingModel model = OpenAiEmbeddingModel.builder()
                .apiKey(ApiKeys.OPENAI_API_KEY)
                .modelName(TEXT_EMBEDDING_3_SMALL)
                .build();

        // 返回的 Embedding 可用于相似度搜索、RAG 检索或聚类等向量场景。
        Response<Embedding> response = model.embed("I love Java");
        Embedding embedding = response.content();

        System.out.println(embedding);
    }
}
