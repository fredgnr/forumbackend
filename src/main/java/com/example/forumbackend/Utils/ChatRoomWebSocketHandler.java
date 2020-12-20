package com.example.forumbackend.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatRoomWebSocketHandler implements WebSocketHandler {
    @Value("socket.user_id")
    private String WEBSOCKET_USER_ID;

    public static final ConcurrentHashMap<Integer,WebSocketSession> sessions=new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
       Integer uid= (Integer) webSocketSession.getAttributes().get(WEBSOCKET_USER_ID);
       sessions.put(uid,webSocketSession);
       System.out.println("放入了"+uid);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if(!webSocketSession.isOpen()){
            Integer uid= (Integer) webSocketSession.getAttributes().get(WEBSOCKET_USER_ID);
            sessions.remove(uid);
            webSocketSession.close();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        Integer uid= (Integer) webSocketSession.getAttributes().get(WEBSOCKET_USER_ID);
        sessions.remove(uid);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Async
    public void sendMessageToUser(Integer userID, TextMessage message){
        if(userID==null){
            return;
        }
        WebSocketSession session;
        if((session=sessions.get(userID))==null){
            return;
        }
        if(!session.isOpen()){
            return;
        }

        try {
            session.sendMessage(message);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
