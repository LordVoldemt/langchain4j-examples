package embedding.model;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.huggingface.HuggingFaceEmbeddingModel;
import dev.langchain4j.model.output.Response;

import static java.time.Duration.ofSeconds;

/**
 * 这个示例学习通过 Hugging Face API 生成文本向量。
 * 运行前需要 HF_API_KEY；waitForModel(true) 表示远端模型冷启动时可以等待加载。
 */
public class HuggingFaceEmbeddingModelExample {

    public static void main(String[] args) {

        // EmbeddingModel 的输出是向量，常用于相似度搜索、分类和 RAG 检索。
        EmbeddingModel embeddingModel = HuggingFaceEmbeddingModel.builder()
                .accessToken(System.getenv("HF_API_KEY"))
                .modelId("sentence-transformers/all-MiniLM-L6-v2")
                .waitForModel(true)
                .timeout(ofSeconds(60))
                .build();

        Response<Embedding> response = embeddingModel.embed("Hello, how are you?");
        System.out.println(response);
    }
}
