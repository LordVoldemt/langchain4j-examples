import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

/**
 * 这个示例学习在 AI Service 中配置共享聊天记忆。
 * 同一个 assistant 实例连续调用时会带上最近消息，因此模型能回答“我叫什么名字”。
 */
public class ServiceWithMemoryExample {

    /**
     * See also {@link ServiceWithMemoryForEachUserExample} and {@link ServiceWithPersistentMemoryExample}.
     * For a low-level {@link ChatMemory} API usage example, see {@link ChatMemoryExamples}.
     */

    interface Assistant {

        String chat(String message);
    }

    public static void main(String[] args) {

        // MessageWindowChatMemory 按消息条数保留历史，简单直观但不按 token 精确控制。
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        ChatModel model = OpenAiChatModel.builder()
                .apiKey(ApiKeys.OPENAI_API_KEY)
                .modelName(GPT_4_O_MINI)
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(model)
                .chatMemory(chatMemory)
                .build();

        String answer = assistant.chat("Hello! My name is Klaus.");
        System.out.println(answer); // Hello Klaus! How can I assist you today?

        String answerWithName = assistant.chat("What is my name?");
        System.out.println(answerWithName); // Your name is Klaus.
    }
}
