package com.example.forumbackend.Service;


import com.example.forumbackend.Domain.Utils.Role;
import com.example.forumbackend.Domain.Normal.User;
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
    public String AccountNotExist;

    @Value("${PasswordWrong}")
    public String PasswordWrong;


    public String login(String account,String password,Role rtmp){
        User user=userService.findByAccount(account);
        String token="";
        rtmp.setToken(token);
        if(user==null){
            System.out.println(1);
            return AccountNotExist;
        }
        else if(!user.getPassword().equals(password)) {
            System.out.println(2);
            return PasswordWrong;
        }
        else{
            //ValueOperations<String,Role> vop=redisTemplate.opsForValue();
            Role tmp=redisTemplate.opsForValue().get(user.getUID().toString());
            if(tmp!=null){
                rtmp.setUser(user);
                token=tmp.getToken();
                System.out.println(3);
            }
            else{
                System.out.println(4);
                token=UUID.randomUUID().toString();
                Role role=new Role(user,token);
                rtmp.setUser(user);
                rtmp.setToken(token);
                redisTemplate.opsForValue().set(user.getUID().toString(),new Role(user,token),60, TimeUnit.MINUTES);
            }
        }
        return token;
    }


    @Transactional
    public void logout(Integer uid){
        redisTemplate.delete(uid.toString());
    }

    public boolean verify(Integer uid ,String token){//验证账号是否登录
        Role tmp=redisTemplate.opsForValue().get(uid.toString());
        if(tmp!=null&&token.equals(tmp.getToken()))
            return true;
        else return false;
    }
}
