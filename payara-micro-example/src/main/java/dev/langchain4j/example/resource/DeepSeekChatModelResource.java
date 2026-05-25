package dev.langchain4j.example.resource;

import dev.langchain4j.model.openai.OpenAiChatModel;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("deepseek")
public class DeepSeekChatModelResource {

    @Inject
    @ConfigProperty(name = "deepseek.api.key")
    private String deepseekApiKey;

    @Inject
    @ConfigProperty(name = "deepseek.chat.model")
    private String modelName;

    private OpenAiChatModel chatModel;

    @PostConstruct
    public void init() {
        // DeepSeek 使用 OpenAI 兼容接口，所以通过 baseUrl 切换到 DeepSeek 服务地址。
        chatModel = OpenAiChatModel.builder()
                .apiKey(deepseekApiKey)
                .baseUrl("https://api.deepseek.com")
                .modelName(modelName)
                .temperature(0.1)
                .build();
    }

    @GET
    @Path("chat")
    @Produces(MediaType.TEXT_PLAIN)
    public String chat(@QueryParam("message") @DefaultValue("What can you tell me about reasoning?") String message) {
        // 对调用方来说仍是普通 REST GET；模型供应商差异被封装在 chatModel 中。
        return chatModel.chat(message);
    }
}
