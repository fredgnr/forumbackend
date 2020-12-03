package com.example.forumbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.example.forumbackend.Mapper")
@SpringBootApplication
@EnableSwagger2
public class ForumbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumbackendApplication.class, args);
    }

}
