package embedding.model;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.vertexai.VertexAiEmbeddingModel;

/**
 * 这个示例学习 Vertex AI 的 embedding 配置项：endpoint、project、location、publisher 和 modelName。
 * 运行前需要本机或环境具备 Google Cloud 认证，否则模型调用会失败。
 */
public class VertexAiEmbeddingModelExample {

    public static void main(String[] args) {

        // Vertex AI 的认证通常来自 gcloud/application default credentials，而不是在代码里写 token。
        EmbeddingModel embeddingModel = VertexAiEmbeddingModel.builder()
                .endpoint("us-central1-aiplatform.googleapis.com:443")
                .project("langchain4j")
                .location("us-central1")
                .publisher("google")
                .modelName("textembedding-gecko@001")
                .build();

        Response<Embedding> response = embeddingModel.embed("Hello, how are you?");
        System.out.println(response);
    }
}
