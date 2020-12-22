package com.example.forumbackend.Config;


import com.example.forumbackend.Utils.Interceptor.MyInterceptor1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private MyInterceptor1 myInterceptor1;

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
         * addInterceptor 注册拦截器
         * addPathPatterns 配置拦截规则
         */
        registry.addInterceptor(myInterceptor1)
            .addPathPatterns("/login/**").excludePathPatterns("/login/in")
            .addPathPatterns("/user/**").excludePathPatterns("/user/singup")
            .addPathPatterns("/reply/**")
            .addPathPatterns("/upfile/**")
            .addPathPatterns("/zan/**")
            .addPathPatterns("/purchase/**")
            .addPathPatterns("/artical/**")
            .addPathPatterns("/resource/**")
            .addPathPatterns("/chat/**");
    }
}