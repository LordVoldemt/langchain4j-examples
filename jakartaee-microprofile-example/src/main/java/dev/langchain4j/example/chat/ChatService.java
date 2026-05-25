package dev.langchain4j.example.chat;

import java.util.logging.Logger;

import org.eclipse.microprofile.metrics.annotation.Timed;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint(value = "/chat", encoders = { ChatMessageEncoder.class })
public class ChatService {

    private static Logger logger = Logger.getLogger(ChatService.class.getName());

    @Inject
    ChatAgent agent = null;

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Server connected to session: " + session.getId());
    }

    @OnMessage
    @Timed(name = "chatProcessingTime", absolute = true,
           description = "Time needed chatting to the agent.")
    public void onMessage(String message, Session session) {

        logger.info("Server received message \"" + message + "\" "
                    + "from session: " + session.getId());

        // WebSocket 每条消息都会进入这里；sessionId 被用作 MemoryId，让不同浏览器连接拥有独立记忆。
        String answer;
        try {
            String sessionId = session.getId();
            answer = agent.chat(sessionId, message);
        } catch (Exception e) {
            answer = "My failure reason is:\n\n" + e.getMessage();
        }

        try {
            // Encoder 会在发送前做简单格式转换，最后由 WebSocket 推回前端。
            session.getBasicRemote().sendObject(answer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("Session " + session.getId()
                    + " was closed with reason " + closeReason.getCloseCode());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.info("WebSocket error for " + session.getId() + " "
                    + throwable.getMessage());
    }

}
