package dev.langchain4j.example.aiservice;

import dev.langchain4j.service.spring.AiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

/**
 * This is an example of using an {@link AiService}, a high-level LangChain4j API.
 *
 * <p>Controller 只负责把 HTTP 请求转换为普通 Java 方法调用。真正的 AI 调用由
 * {@link Assistant} 和 {@link StreamingAssistant} 这两个 LangChain4j 代理接口完成。</p>
 */
@RestController
public class AssistantController {

    private final Assistant assistant;
    private final StreamingAssistant streamingAssistant;

    public AssistantController(Assistant assistant, StreamingAssistant streamingAssistant) {
        this.assistant = assistant;
        this.streamingAssistant = streamingAssistant;
    }

    @GetMapping("/assistant")
    public String assistant(@RequestParam(value = "message", defaultValue = "What is the current time?") String message) {
        // 调用看起来像普通 Java 接口，LangChain4j 会在运行时把它代理成一次聊天模型请求。
        return assistant.chat(message);
    }

    @GetMapping(value = "/streamingAssistant", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamingAssistant(
            @RequestParam(value = "message", defaultValue = "What is the current time?") String message) {
        // TEXT_EVENT_STREAM_VALUE 表示 SSE 流式响应；Flux 中的每个片段都会逐步推送给客户端。
        return streamingAssistant.chat(message);
    }
}
