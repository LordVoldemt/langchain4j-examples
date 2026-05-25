import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.watsonx.WatsonxChatModel;

class WatsonxChatModelTest {

    public static void main(String... args) {

        try {

            // Watsonx 需要 baseUrl、apiKey 和 projectId 一起定位 IBM Cloud 项目中的模型调用。
            ChatModel model = WatsonxChatModel.builder()
                .baseUrl(System.getenv("WATSONX_URL"))
                .apiKey(System.getenv("WATSONX_API_KEY"))
                .projectId(System.getenv("WATSONX_PROJECT_ID"))
                .modelName("ibm/granite-4-h-small")
                .build();

            System.out.println("--------------------------------------------------");
            System.out.println("Granite says: " + model.chat("What is the capital of Italy?"));
            System.out.println("--------------------------------------------------");


            // 单次 ChatRequest 可以覆盖 modelName，用同一个客户端尝试不同 provider 模型。
            ChatRequest request = ChatRequest.builder()
                .messages(UserMessage.from("What is the capital of Italy?"))
                .modelName("mistralai/mistral-small-3-1-24b-instruct-2503")
                .build();

            System.out.println("--------------------------------------------------");
            System.out.println("mistral-small says: " + model.chat(request).aiMessage().text());
            System.out.println("--------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
