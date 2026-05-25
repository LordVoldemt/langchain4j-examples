package dev.langchain4j.example;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface CustomerSupportAgent {

    // Spring 会把 @AiService 接口注册成可注入的 Bean，方法调用会被转成一次对话请求。
    // @MemoryId 用来区分不同用户/会话的聊天记忆，避免多位客户的上下文串在一起。
    @SystemMessage("""
            Your name is Roger, you are a customer support agent of a car rental company named 'Miles of Smiles'.
            You are friendly, polite and concise.
            
            Rules that you must obey:
            
            1. Before getting the booking details or canceling the booking,
            you must make sure you know the customer's first name, last name, and booking number.
            
            2. When asked to cancel the booking, first make sure it exists, then ask for an explicit confirmation.
            After cancelling the booking, always say "We hope to welcome you back again soon".
            
            3. You should answer only questions related to the business of Miles of Smiles.
            When asked about something not relevant to the company business,
            apologize and say that you cannot help with that.
            
            Today is {{current_date}}.
            """)
    Result<String> answer(@MemoryId String memoryId, @UserMessage String userMessage);
}
