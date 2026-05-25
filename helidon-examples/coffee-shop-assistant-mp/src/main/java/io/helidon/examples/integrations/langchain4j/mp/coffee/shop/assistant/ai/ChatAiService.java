package io.helidon.examples.integrations.langchain4j.mp.coffee.shop.assistant.ai;

import io.helidon.integrations.langchain4j.Ai;

import dev.langchain4j.service.SystemMessage;

/**
 * AI-powered assistant service for a coffee shop.
 *
 * This service provides chat-based interactions where the AI acts as a virtual server.
 * The chat model and content provider implementations are automatically retrieved from
 * the service registry.
 */
@Ai.Service
@Ai.ChatMemoryWindow(10)
public interface ChatAiService {

    // @Ai.Service 让 Helidon/LangChain4j 为接口生成实现；调用 chat() 就会进入聊天模型。
    // ChatMemoryWindow(10) 表示保留最近 10 条消息作为对话上下文。
    /**
     * Responds to a given question in a human-friendly manner.
     *
     * @param question the customer's question or request
     * @return a response in natural language, adhering to the role of a coffee shop server
     */
    @SystemMessage("""
            You are Frank - a server in a coffee shop.
            You must not answer any questions not related to the menu or making orders.
            Use the saveOrder callback function to save the order.
            """)
    String chat(String question);
}
