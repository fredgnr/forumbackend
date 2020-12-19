package com.example.forumbackend.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
    public   Integer getuid(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies)
            if(cookie.getName().equals("UID"))
                return new Integer(cookie.getValue());
        return null;
    }

    public String gettoken(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies)
            if(cookie.getName().equals("token"))
                return cookie.getValue();
        return null;
    }

}
