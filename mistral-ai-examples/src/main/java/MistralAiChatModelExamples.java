import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.mistralai.MistralAiChatModel;

import static dev.langchain4j.model.mistralai.MistralAiChatModelName.MISTRAL_SMALL_LATEST;

public class MistralAiChatModelExamples {

    static class Simple_Prompt {

        public static void main(String[] args) {

            // Mistral 示例从环境变量读取 API key；modelName 选择 Mistral 托管的聊天模型。
            ChatModel model = MistralAiChatModel.builder()
                    .apiKey(System.getenv("MISTRAL_AI_API_KEY")) // Please use your own Mistral AI API key
                    .modelName(MISTRAL_SMALL_LATEST)
                    .logRequests(true)
                    .logResponses(true)
                    .build();

            String joke = model.chat("Tell me a joke about Java");

            System.out.println(joke);
        }
    }
}
