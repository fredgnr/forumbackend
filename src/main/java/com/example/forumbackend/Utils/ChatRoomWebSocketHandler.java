package com.example.forumbackend.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

    public boolean sendMessageToUser(Integer userID,TextMessage message){
        for (Map.Entry<Integer, WebSocketSession> integerWebSocketSessionEntry : sessions.entrySet()) {
            Object key = ((Map.Entry) integerWebSocketSessionEntry).getKey();
            System.out.println("test"+key);
            Object val = ((Map.Entry) integerWebSocketSessionEntry).getValue();
        }
        if(userID==null){
            System.out.println(1);
            return  false;
        }
        WebSocketSession session;
        if((session=sessions.get(userID))==null){
            System.out.println(2);
            return false;
        }
        if(!session.isOpen()){
            System.out.println(3);
            return false;
        }

        try {
            System.out.println(4);
            session.sendMessage(message);
        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }
}
