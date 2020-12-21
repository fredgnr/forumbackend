package com.example.forumbackend.Utils;

import com.example.forumbackend.Domain.Normal.User;
import com.example.forumbackend.Domain.Utils.Role;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class MyInterceptorfirst implements HandlerInterceptor {
    //Logger logger = LoggerFactory.getLogger(MyInterceptor1.class);

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
       response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        return true;
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