package com.example.forumbackend.Config;

import com.example.forumbackend.Utils.ChatRoomWebSocketHandler;
import com.example.forumbackend.Utils.WebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
    @Autowired
    private WebSocketInterceptor webSocketInterceptor;

    @Autowired
    private ChatRoomWebSocketHandler chatRoomWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("handler:"+chatRoomWebSocketHandler);
        registry.addHandler(chatRoomWebSocketHandler,"/chat-room/{userId}")
                .addInterceptors(webSocketInterceptor).setAllowedOrigins("*");
    }
}
