package com.example.forumbackend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class UtilsConfig {

    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy/MM/dd/");
    }
}
