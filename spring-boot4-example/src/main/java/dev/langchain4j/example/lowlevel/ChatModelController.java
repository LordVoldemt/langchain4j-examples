package dev.langchain4j.example.lowlevel;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is an example of using a {@link ChatModel}, a low-level LangChain4j API.
 *
 * <p>这是一个最直接的 REST 入口示例：浏览器或 HTTP 客户端访问 {@code /model}，
 * Spring MVC 调用这个 Controller，再把用户输入交给 LangChain4j 的底层 {@link ChatModel}。</p>
 */
@RestController
public class ChatModelController {

    private final ChatModel chatModel;

    // ChatModel 由 Spring 容器注入，业务代码不需要自己读取 API key 或创建 HTTP 客户端。
    public ChatModelController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/model")
    public String model(@RequestParam(value = "message", defaultValue = "Hello") String message) {
        // 同步调用模型：请求线程会等待模型返回完整文本后，再把结果作为 HTTP 响应返回。
        return chatModel.chat(message);
    }
}
