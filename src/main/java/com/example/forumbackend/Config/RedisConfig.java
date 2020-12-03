package com.example.forumbackend.Config;


import com.example.forumbackend.Domain.Role;
import com.example.forumbackend.Utils.RedisUtils.RedisObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean(value = "logintemplate")
    public RedisTemplate<String, Role> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Role> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
