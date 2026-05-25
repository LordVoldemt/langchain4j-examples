package dev.langchain4j.example.aiservice;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

@AiService
public interface StreamingAssistant {

    // 返回 Flux<String> 表示模型会边生成边返回 token/文本片段，适合接到 SSE 或响应式接口。
    @SystemMessage("You are a polite assistant")
    Flux<String> chat(String userMessage);
}
