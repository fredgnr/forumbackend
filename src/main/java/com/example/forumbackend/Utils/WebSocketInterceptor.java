package com.example.forumbackend.Utils;

import ch.qos.logback.classic.turbo.TurboFilter;
import com.example.forumbackend.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
@Component
public class WebSocketInterceptor implements HandshakeInterceptor {

    @Value("socket.user_id")
    private String WEBSOCKET_USER_ID;

    @Autowired
    private LoginService loginService;

    @Autowired
    private CookieUtil cookieUtil;

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if(serverHttpRequest instanceof ServletServerHttpRequest){
            ServletServerHttpRequest request=(ServletServerHttpRequest) serverHttpRequest;
            Integer uid=cookieUtil.getuid(request.getServletRequest());
            String token=cookieUtil.gettoken(request.getServletRequest());
            if (loginService.verify(uid,token)){
                map.put(WEBSOCKET_USER_ID,uid);
                return true;
            }

           /* ServletServerHttpRequest request = (ServletServerHttpRequest)serverHttpRequest;
            // 将 url 分割成两部分, userId= 前和 userId= 后
            // 比如 http://localhost:8080/chat/userId=1234
            // 被分割成 http://localhost:8080/chat 和 1234, 1234就是我们需要的id
            String userId = request.getURI().toString().split("userId=")[1];
            map.put(WEBSOCKET_USER_ID, new Integer(userId));
            System.out.println("新加入：\t"+userId);
            return true;*/
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
