import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiTokenCountEstimator;

import static dev.langchain4j.data.message.UserMessage.userMessage;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

/**
 * 这个示例学习低层 ChatMemory：由你决定哪些消息进入上下文，再把消息列表交给模型。
 * 如果只想快速做多轮对话，可以先看 AI Services 版本；这里更适合理解记忆的底层机制。
 */
public class ChatMemoryExamples {

    /**
     * This example demonstrates how to use a low-level {@link ChatMemory} API.
     * For a high-level API with AI Services see {@link ServiceWithMemoryExample}.
     */

    public static void main(String[] args) {

        // TokenWindowChatMemory 会按 token 上限裁剪历史，避免上下文超过模型限制。
        ChatMemory chatMemory = TokenWindowChatMemory.withMaxTokens(300, new OpenAiTokenCountEstimator(GPT_4_O_MINI));

        ChatModel model = OpenAiChatModel.builder()
                .apiKey(ApiKeys.OPENAI_API_KEY)
                .modelName(GPT_4_O_MINI)
                .build();

        // You have full control over the chat memory.
        // You can decide if you want to add a particular message to the memory
        // (e.g. you might not want to store few-shot examples to save on tokens).
        // You can process/modify the message before saving if required.

        chatMemory.add(userMessage("Hello, my name is Klaus"));
        AiMessage answer = model.chat(chatMemory.messages()).aiMessage();
        System.out.println(answer.text()); // Hello Klaus! How can I assist you today?
        chatMemory.add(answer);

        chatMemory.add(userMessage("What is my name?"));
        AiMessage answerWithName = model.chat(chatMemory.messages()).aiMessage();
        System.out.println(answerWithName.text()); // Your name is Klaus.
        chatMemory.add(answerWithName);
    }
}
