package com.example.forumbackend.Service;


import com.example.forumbackend.Domain.Role;
import com.example.forumbackend.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {

    @Autowired
    RedisTemplate<String, Role> redisTemplate;

    @Autowired
    UserService userService;

    @Value("${AccountNotExist}")
    public static String AccountNotExist;

    @Value("${PasswordWrong}")
    public static String PasswordWrong;


    public String login(String account,String password,Role rtmp){
        User user=userService.findByAccount(account);
        String token="";
        rtmp.setToken(token);
        if(user==null){
            token=AccountNotExist;
        }
        else if(!user.getPassword().equals(password))
            token=PasswordWrong;
        else{
            //ValueOperations<String,Role> vop=redisTemplate.opsForValue();
            Role tmp=redisTemplate.opsForValue().get(user.getUID().toString());
            if(tmp!=null){
                rtmp.setUser(user);
                token=tmp.getToken();
            }
            else{
                token=UUID.randomUUID().toString();
                Role role=new Role(user,token);
                rtmp.setUser(user);
                rtmp.setToken(token);
                System.out.println("insertin:\t"+token+role.toString());
                redisTemplate.opsForValue().set(user.getUID().toString(),new Role(user,token),60, TimeUnit.MINUTES);
                role=redisTemplate.opsForValue().get(user.getUID().toString());
                System.out.println("testinsert:\t"+role);
            }
        }
        return token;
    }


    @Transactional
    public void logout(Integer uid){
        redisTemplate.delete(uid.toString());
    }
}
