import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import static java.time.Duration.ofSeconds;

/**
 * 这个示例学习如何配置模型参数：温度、超时和请求/响应日志。
 * 调参会影响输出稳定性、等待时间和调试信息，但不会改变 LangChain4j 的调用方式。
 */
public class _01_ModelParameters {

    public static void main(String[] args) {

        // OpenAI parameters are explained here: https://platform.openai.com/docs/api-reference/chat/create

        // builder 上的参数会原样传给底层模型服务，新手可以先关注 temperature 和 timeout。
        ChatModel model = OpenAiChatModel.builder()
                .apiKey(ApiKeys.OPENAI_API_KEY)
                .modelName(GPT_4_O_MINI)
                .temperature(0.3)
                .timeout(ofSeconds(60))
                .logRequests(true) // 调试时有用；生产环境要注意日志中可能包含用户输入。
                .logResponses(true)
                .build();

        String prompt = "Explain in three lines how to make a beautiful painting";

        String response = model.chat(prompt);

        System.out.println(response);
    }
}
