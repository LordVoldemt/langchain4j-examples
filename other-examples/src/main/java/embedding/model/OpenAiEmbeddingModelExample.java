package embedding.model;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.output.Response;

import static dev.langchain4j.model.openai.OpenAiEmbeddingModelName.TEXT_EMBEDDING_3_SMALL;

/**
 * 这个示例学习 OpenAI EmbeddingModel：把文本转成可做相似度搜索的向量。
 * "demo" 只适合演示配置结构，真实调用请改为从环境变量读取密钥，避免硬编码真实 key。
 */
public class OpenAiEmbeddingModelExample {

    public static void main(String[] args) {

        EmbeddingModel embeddingModel = OpenAiEmbeddingModel.builder()
                .apiKey("demo")
                .modelName(TEXT_EMBEDDING_3_SMALL)
                .build();

        // embed() 返回 Response<Embedding>，向量本身在 content() 中。
        Response<Embedding> response = embeddingModel.embed("Hello, how are you?");
        System.out.println(response);
    }
}
