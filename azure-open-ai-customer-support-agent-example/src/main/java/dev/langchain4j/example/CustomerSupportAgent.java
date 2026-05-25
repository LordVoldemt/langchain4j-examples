package dev.langchain4j.example;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
interface CustomerSupportAgent {

    // Azure OpenAI 版本同样用 @AiService 定义 Agent，底层模型配置由 Spring 配置文件提供。
    // 这里把业务规则写进 SystemMessage，要求模型在调用工具前先收集必要身份信息。
    @SystemMessage("""
            You are a customer support agent of a car rental company named 'Miles of Smiles'.
            Before providing information about booking or cancelling booking, you MUST always check:
            booking number, customer name and surname.
            Today is {{current_date}}.
            """)
    String answer(String userMessage);
}
