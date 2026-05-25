import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.github.GitHubModelsEmbeddingModel;
import dev.langchain4j.model.output.Response;

import static dev.langchain4j.model.github.GitHubModelsEmbeddingModelName.TEXT_EMBEDDING_3_SMALL;

public class GitHubModelsEmbeddingModelExamples {

    static class Simple_Embedding {

        public static void main(String[] args) {

            // GitHub Models 的 embedding 也需要选择 embedding 模型，返回向量可用于语义搜索。
            GitHubModelsEmbeddingModel model = GitHubModelsEmbeddingModel.builder()
                    .gitHubToken(System.getenv("GITHUB_TOKEN"))
                    .modelName(TEXT_EMBEDDING_3_SMALL)
                    .logRequestsAndResponses(true)
                    .build();

            Response<Embedding> response = model.embed("Please embed this sentence.");

            System.out.println(response);
        }
    }
}
