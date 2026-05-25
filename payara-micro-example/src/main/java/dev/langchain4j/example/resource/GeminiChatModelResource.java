package dev.langchain4j.example.resource;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("gemini")
public class GeminiChatModelResource {

    @Inject
    @ConfigProperty(name = "google-ai-gemini.chat-model.api-key")
    private String geminiApiKey;

    @Inject
    @ConfigProperty(name = "google-ai-gemini.chat-model.model-name")
    private String modelName;

    private GoogleAiGeminiChatModel chatModel;

    @PostConstruct
    public void init() {
        // 生命周期回调中创建模型客户端，避免每个请求都重复构建。
        chatModel = GoogleAiGeminiChatModel.builder()
                .apiKey(geminiApiKey)
                .modelName(modelName)
                .temperature(0.7)
                .build();
    }

    @GET
    @Path("chat")
    @Produces(MediaType.TEXT_PLAIN)
    public String chat(@QueryParam("message") @DefaultValue("Tell me a joke about programming.") String message) {
        // Resource 负责 HTTP 边界，chatModel 负责和 Gemini 兼容模型服务通信。
        return chatModel.chat(message);
    }
}
