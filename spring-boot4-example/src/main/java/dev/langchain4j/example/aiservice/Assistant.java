package dev.langchain4j.example.aiservice;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Assistant {

    // @SystemMessage 是给模型的固定角色设定；方法参数 userMessage 才是每次请求的用户输入。
    @SystemMessage("You are a polite assistant")
    String chat(String userMessage);
}
