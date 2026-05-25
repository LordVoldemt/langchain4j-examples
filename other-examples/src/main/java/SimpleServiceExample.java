import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

/**
 * 这个示例学习最简单的 AI Service：用接口方法替代直接调用 ChatModel。
 * 这种方式适合在业务代码中定义“助手能力”，让 LangChain4j 负责代理实现。
 */
public class SimpleServiceExample {

    interface Assistant {

        String chat(String message);
    }

    public static void main(String[] args) {

        ChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(ApiKeys.OPENAI_API_KEY)
                .modelName(GPT_4_O_MINI)
                .build();

        // AiServices.create 会基于接口生成代理，对 assistant.chat() 的调用会转成一次模型请求。
        Assistant assistant = AiServices.create(Assistant.class, chatModel);

        String answer = assistant.chat("Hello");

        System.out.println(answer); // Hello! How can I assist you today?
    }
}
