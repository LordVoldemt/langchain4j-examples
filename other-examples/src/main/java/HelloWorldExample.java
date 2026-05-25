import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

/**
 * 最小聊天示例：创建 ChatModel，然后发送一条用户消息。
 * 运行真实 OpenAI 请求前，请通过 OPENAI_API_KEY 环境变量提供密钥。
 */
public class HelloWorldExample {

    public static void main(String[] args) {

        // Create an instance of a model
        // builder 用来配置模型供应商和模型名，业务代码只依赖统一的 ChatModel 接口。
        ChatModel model = OpenAiChatModel.builder()
                .apiKey(ApiKeys.OPENAI_API_KEY)
                .modelName(GPT_4_O_MINI)
                .build();

        // Start interacting
        String answer = model.chat("Hello world!");

        System.out.println(answer); // Hello! How can I assist you today?
    }
}
