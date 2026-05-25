package dev.langchain4j.example.chat;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;

public class ChatMessageEncoder implements Encoder.Text<String> {

    @Override
    public String encode(String message) throws EncodeException {

        // Encoder 是 WebSocket 发送响应前的最后一步，这里把换行转换为浏览器可显示的 <br/>。
        if (!message.endsWith(".")) {
            message += " ...";
        }

        message = message.replaceAll("\n", "<br/>");

        return message;

    }

}
