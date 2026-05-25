/*
 * Copyright The WildFly Authors
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.langchain4j.example.wildfly;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;
import java.util.List;

@RequestScoped
@Path("/sse")
public class SseResource {

    @Inject
    @Named(value = "streaming-ollama")
    StreamingChatLanguageModel streamingChatModel;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Path("/chat")
    public void streamingChatWithAssistant(@Context Sse sse, @Context SseEventSink sseEventSink,
            @HeaderParam(HttpHeaders.LAST_EVENT_ID_HEADER) @DefaultValue("-1") int lastReceivedId,
            @QueryParam("question") String question) throws InterruptedException {
        // SSE 是单向流式响应：浏览器发起一次 GET，服务端随后不断写入事件。
        // Last-Event-ID 用于客户端断线重连时告诉服务端最后收到的事件编号。
        final int lastEventId;
        if (lastReceivedId != -1) {
            lastEventId = lastReceivedId + 1;
        } else {
            lastEventId = 1;
        }
        System.out.println("Receiving a request on SSE endpoint");
        List<ChatMessage> messages = List.of(SystemMessage.from("""
                   You are an AI named Bob answering general question.
                   Your response must be polite, use the same language as the question, and be relevant to the question."""),
                UserMessage.from(question));
        SseBroadcasterStreamingResponseHandler handler = new SseBroadcasterStreamingResponseHandler(sseEventSink, sse, lastEventId);
        System.out.println("streamingChatWithAssistant called within:" + Thread.currentThread());
        // streamingChatModel 会异步产生片段，handler 负责把每个片段转换成 SSE 事件发给客户端。
        streamingChatModel.chat(messages, handler);
    }
}
