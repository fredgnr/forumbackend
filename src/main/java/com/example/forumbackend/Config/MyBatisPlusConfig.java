package com.example.forumbackend.Config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;

@Configuration
@EnableTransactionManagement
public class MyBatisPlusConfig {

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        paginationInterceptor.setDialectType("mysql");
        // 默认false
        // 设置请求的页面大于最大页后操作， true调回到首页;false继续请求,返回空数据.
        paginationInterceptor.setOverflow(false);
        return paginationInterceptor;
    }
}