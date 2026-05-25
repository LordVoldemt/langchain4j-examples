import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.model.anthropic.AnthropicTokenUsage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static dev.langchain4j.internal.Utils.readBytes;
import static dev.langchain4j.model.anthropic.AnthropicChatModelName.CLAUDE_HAIKU_4_5_20251001;
import static org.assertj.core.api.Assertions.assertThat;

class AnthropicChatModelTest {

    ChatModel model = AnthropicChatModel.builder()
            // API key can be created here: https://console.anthropic.com/settings/keys
            .apiKey(System.getenv("ANTHROPIC_API_KEY"))
            // modelName 决定 Claude 的具体版本；不同版本支持的视觉、缓存和上下文能力可能不同。
            .modelName("claude-3-haiku-20240307")
            .logRequests(true)
            .logResponses(true)
            // Other parameters can be set as well
            .build();

    @Test
    void AnthropicChatModel_Example() {

        String answer = model.chat("What is the capital of Germany?");

        assertThat(answer).containsIgnoringCase("Berlin");
    }

    @Test
    void AnthropicChatModel_with_vision_Example() {

        byte[] image = readBytes("https://docs.langchain4j.dev/img/langchain4j-components.png");
        String base64EncodedImage = Base64.getEncoder().encodeToString(image);

        // Anthropic 视觉输入这里以 base64 + MIME type 传入，和纯文本消息一起组成 UserMessage。
        UserMessage userMessage = UserMessage.from(
                TextContent.from("What do you see?"),
                ImageContent.from(base64EncodedImage, "image/png")
        );

        ChatResponse chatResponse = model.chat(userMessage);

        assertThat(chatResponse.aiMessage().text()).containsIgnoringCase("RAG");
    }

    @Test
    void AnthropicChatModel_with_cache_system_message_Example() {
        ChatModel modelWithCache = AnthropicChatModel.builder()
                .apiKey(System.getenv("ANTHROPIC_API_KEY"))
                // prompt caching 需要启用对应 beta，并通过 cacheSystemMessages 缓存长 system message。
                .beta("prompt-caching-2024-07-31")
                .modelName(CLAUDE_HAIKU_4_5_20251001)
                .cacheSystemMessages(true)
                .logRequests(true)
                .logResponses(true)
                .build();

        // Now cache has in beta
        // You can send up to 4 systemMessages/Tools

        // create cache
        SystemMessage systemMessage = SystemMessage.from("What types of messages are supported in LangChain?".repeat(187));
        UserMessage userMessage = UserMessage.userMessage("what result it calcule 5x2 + 2x + 2 = 0?");
        ChatResponse response = modelWithCache.chat(systemMessage, userMessage);

        AnthropicTokenUsage createCacheTokenUsage = (AnthropicTokenUsage) response.metadata().tokenUsage();
        assertThat(createCacheTokenUsage.cacheCreationInputTokens()).isGreaterThan(0);

        // read cache created
        ChatResponse responseToReadCache = modelWithCache.chat(systemMessage, userMessage);
        AnthropicTokenUsage readCacheTokenUsage = (AnthropicTokenUsage) responseToReadCache.metadata().tokenUsage();
        assertThat(readCacheTokenUsage.cacheReadInputTokens()).isGreaterThan(0);
    }
}
