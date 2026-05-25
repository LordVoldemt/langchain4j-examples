import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ChatRequestParameters;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.vertexai.gemini.VertexAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class VertexAiGeminiChatModelTest {

    /**
     * 1. Enable Vertex AI in Google Cloud Console
     * 2. Set your own project and location below
     */
    private static final String PROJECT = "langchain4j";
    private static final String LOCATION = "us-central1";
    private static final String MODEL_NAME = "gemini-pro";

    ChatModel model = VertexAiGeminiChatModel.builder()
            // project/location/modelName 共同决定调用哪个 Vertex AI 区域中的 Gemini 模型。
            .project(PROJECT)
            .location(LOCATION)
            .modelName(MODEL_NAME)
            .temperature(0.0f)
            .build();

    static class Calculator {

        @Tool("Adds two given numbers")
        double add(double a, double b) {
            System.out.printf("Called add(%s, %s)%n", a, b);
            return a + b;
        }
    }

    @Test
    void Low_level_Tools_Example() {

        // 低层 tools API 只让模型规划工具调用，应用侧仍要负责执行 Calculator。
        List<ToolSpecification> toolSpecifications = ToolSpecifications.toolSpecificationsFrom(new Calculator());

        ChatRequest chatRequest = ChatRequest.builder()
                .messages(UserMessage.from("How much is 754 + 926?"))
                .parameters(ChatRequestParameters.builder()
                        .toolSpecifications(toolSpecifications)
                        .build())
                .build();

        ChatResponse chatResponse = model.chat(chatRequest);

        AiMessage aiMessage = chatResponse.aiMessage();
        assertThat(aiMessage.hasToolExecutionRequests()).isTrue();
        assertThat(aiMessage.toolExecutionRequests()).hasSize(1);

        ToolExecutionRequest toolExecutionRequest = aiMessage.toolExecutionRequests().get(0);
        assertThat(toolExecutionRequest.name()).isEqualTo("add");
        assertThat(toolExecutionRequest.arguments())
                .isEqualToIgnoringWhitespace("{\"arg1\":926.0,\"arg0\":754.0}");
    }

    interface Assistant {

        String chat(String userMessage);
    }

    @Test
    void High_level_Tools_Example() {

        Calculator calculator = spy(new Calculator());

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(model)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                // 高层 AiServices 会自动完成工具执行和结果回传，适合业务代码少写样板逻辑。
                .tools(calculator)
                .build();

        String answer = assistant.chat("How much is 754 + 926?");
        System.out.println(answer);

        verify(calculator).add(754, 926);
        verifyNoMoreInteractions(calculator);
    }
}
