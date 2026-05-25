import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.List;
import java.util.Map;

import static dev.langchain4j.data.message.ChatMessageDeserializer.messagesFromJson;
import static dev.langchain4j.data.message.ChatMessageSerializer.messagesToJson;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import static org.mapdb.Serializer.STRING;

/**
 * 这个示例学习持久化聊天记忆：即使程序重启，也能从 ChatMemoryStore 读回历史消息。
 * 示例使用本地 MapDB 文件，运行后会产生 chat-memory.db；生产环境通常会换成自己的存储。
 */
public class ServiceWithPersistentMemoryExample {

    /**
     * See also {@link ServiceWithMemoryExample} and {@link ServiceWithPersistentMemoryForEachUserExample}.
     */

    interface Assistant {

        String chat(String message);
    }

    public static void main(String[] args) {

        // 将 ChatMemoryStore 接入 MessageWindowChatMemory 后，LangChain4j 会在更新记忆时回调存储实现。
        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .maxMessages(10)
                .chatMemoryStore(new PersistentChatMemoryStore())
                .build();

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

        // Now, comment out the two lines above, uncomment the two lines below, and run again.

        // String answerWithName = assistant.chat("What is my name?");
        // System.out.println(answerWithName); // Your name is Klaus.
    }

    // You can create your own implementation of ChatMemoryStore and store chat memory whenever you'd like
    // 中文提示：这里把消息序列化为 JSON 存储，便于跨进程或重启后恢复上下文。
    static class PersistentChatMemoryStore implements ChatMemoryStore {

        private final DB db = DBMaker.fileDB("chat-memory.db").transactionEnable().make();
        private final Map<String, String> map = db.hashMap("messages", STRING, STRING).createOrOpen();

        @Override
        public List<ChatMessage> getMessages(Object memoryId) {
            String json = map.get((String) memoryId);
            return messagesFromJson(json);
        }

        @Override
        public void updateMessages(Object memoryId, List<ChatMessage> messages) {
            String json = messagesToJson(messages);
            map.put((String) memoryId, json);
            db.commit();
        }

        @Override
        public void deleteMessages(Object memoryId) {
            map.remove((String) memoryId);
            db.commit();
        }
    }
}
