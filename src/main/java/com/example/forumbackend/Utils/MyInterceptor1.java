package com.example.forumbackend.Utils;

import com.example.forumbackend.Domain.Role;
import com.example.forumbackend.Domain.User;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class MyInterceptor1 implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(MyInterceptor1.class);

    @Value("${cookie.timeout}")
    private Integer cookie_timeout;

    @Autowired
    ObjectMapper objectMapper;

    @Qualifier("logintemplate")
    @Autowired
    RedisTemplate<String, Role> redisTemplate;

    /**
     * 在请求到达Controller控制器之前 通过拦截器执行一段代码
     * 如果方法返回true,继续执行后续操作
     * 如果返回false，执行中断请求处理，请求不会发送到Controller
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("拦截器1 在控制器执行之前执行");
        Cookie[] cookies=request.getCookies();
        Cookie usercookie=null,tokencookie=null;
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("UID")) {
                usercookie = cookie;
                System.out.println(cookie.getValue());
            }
            else if (cookie.getName().equals("token")) {
                tokencookie = cookie;
                System.out.println(cookie.getValue());
            }
        }

        ResponseResult<User> result=null;
        if(usercookie==null||tokencookie==null)
            result= Response.makeErrRsp("Error");
        else{
            ValueOperations<String,Role> vop=redisTemplate.opsForValue();
            Role role=vop.get(usercookie.getValue());
            if(role==null){
                System.out.println("role null");
                result=Response.makeErrRsp("未登录或登录已失效");
            }
            else if(!role.getToken().equals(tokencookie.getValue()))
                result=Response.makeErrRsp("token错误");
            else{
                //刷新缓存以及cookie有效时间
                redisTemplate.opsForValue().set(role.getUser().getUID().toString(),role,cookie_timeout, TimeUnit.MINUTES);
                usercookie.setMaxAge(60*cookie_timeout);
                tokencookie.setMaxAge(60*cookie_timeout);
                return  true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null ;
        out = response.getWriter();
        out.write(objectMapper.writeValueAsString(result));
        out.flush();
        out.close();
        return false;
    }

    /**
     * 控制器之后，跳转前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //logger.info("拦截器1 在控制器执行之后执行");

    }

    /**
     * 跳转之后执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //logger.info("拦截器1 最后执行");
    }
}