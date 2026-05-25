import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.azure.AzureOpenAiEmbeddingModel;
import dev.langchain4j.model.output.Response;

public class AzureOpenAiEmbeddingModelExamples {

    static class Simple_Embedding {

        public static void main(String[] args) {

            // embedding 示例要使用专门的 embedding deployment，而不是聊天模型的 deployment。
            AzureOpenAiEmbeddingModel model = AzureOpenAiEmbeddingModel.builder()
                    .apiKey(System.getenv("AZURE_OPENAI_KEY"))
                    .endpoint(System.getenv("AZURE_OPENAI_ENDPOINT"))
                    .deploymentName(System.getenv("AZURE_OPENAI_EMBEDDING_DEPLOYMENT_NAME"))
                    .logRequestsAndResponses(true)
                    .build();

            // embed() 返回向量响应，可继续写入向量库用于语义检索。
            Response<Embedding> response = model.embed("Please embed this sentence.");

            System.out.println(response);
        }
    }
}
