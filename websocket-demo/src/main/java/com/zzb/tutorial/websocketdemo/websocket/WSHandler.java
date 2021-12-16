package com.zzb.tutorial.websocketdemo.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

public class WSHandler implements WebSocketHandler {

    private Logger log = LoggerFactory.getLogger(WSHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("WSHandler afterConnectionEstablished");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("WSHandler handleMessage, message = " + message);

        String content = (String) message.getPayload();
        TextMessage textMessage = new TextMessage("Server收到你的消息了, 消息内容为: " + content);
        session.sendMessage(textMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        exception.printStackTrace();
        log.info("WSHandler handleTransportError");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("WSHandler afterConnectionClosed, closeStatus = " + closeStatus);
    }

    @Override
    public boolean supportsPartialMessages() {
        log.info("WSHandler supportsPartialMessages");

        return false;
    }
}
