package com.example.forumbackend.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.forumbackend.Domain.Normal.User;

public class TokenUtils {
    public String gettoken(User user){
        String token="";
        token= JWT.create()
                .withAudience(user.getAccount())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
