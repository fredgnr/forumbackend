package com.example.forumbackend.Contoller;

import com.example.forumbackend.Domain.Role;
import com.example.forumbackend.Domain.User;
import com.example.forumbackend.Domain.User_Info;
import com.example.forumbackend.Mapper.UserInfoMapper;
import com.example.forumbackend.Service.UserInfoService;
import com.example.forumbackend.Service.UserService;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    private final Logger logger= LoggerFactory.getLogger(UserController.class);

    private static Integer getuid(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies)
            if(cookie.getName().equals("UID"))
                return new Integer(cookie.getValue());
        return null;
    }

    @Transactional
    @GetMapping("/byuid")
    @ApiOperation(value = "根据cookie中的uid获取自己的账户类")
    public ResponseResult<User> getuserbyuid(
             HttpServletRequest request) {
        logger.info("nums of cookies:\t"+request.getCookies().length);
        Integer uidincookie=getuid(request);
        return Response.makeOKRsp(userService.findByUID(uidincookie));
    }

    @Transactional
    @PostMapping("/singup")
    @ApiOperation(value="注册账号")
    public ResponseResult<User> singup(@RequestBody User user){
        if(userService.findByAccount(user.getAccount())!=null){
            return Response.makeRsp(
                    ResultCode.ACCOUNT_DUPLICATED.code, user.getAccount()+"\t账号已被注册");
        }
        else if(userService.findByEmail(user.getEmail())!=null){
            return Response.makeRsp(ResultCode.EMAIL_DUPLICATED.code, user.getEmail()+"邮箱已被注册");
        }
        else{
            userService.insertUser(user);
            User_Info userInfo=new User_Info();
            userInfo.setUser_ID(user.getUID());
            userInfo.setUser_Intro("");
            userInfo.setUser_Zan(0);
            userInfo.setUser_Balance(0);
            userInfo.setUser_Point(0);
            userInfoService.insertinfo(userInfo);
            return Response.makeOKRsp(user);
        }
    }

    @Transactional
    @PutMapping("/changename")
    @ApiOperation(value = "修改姓名，具体账号从cookie中获取")
    public ResponseResult<User> changename(HttpServletRequest request,String name){
        Integer uidincookie=getuid(request);
        userService.changeName(uidincookie,name);
        return Response.makeOKRsp();
    }

    @Transactional
    @PutMapping("/changepassword")
    @ApiOperation(value = "修改密码，账号从cookie中获取")
    public ResponseResult<User> changepassword(HttpServletRequest request,String password){
        Integer uidincookie=getuid(request);
        userService.changePassword(uidincookie,password);
        return Response.makeOKRsp();
    }

    @Transactional
    @PutMapping("/changeintro")
    @ApiOperation(value = "修改个人简介，账号从cookie中获取")
    public ResponseResult<User_Info> changeintro(HttpServletRequest request,String intro){
        Integer uidincookie=getuid(request);
        userInfoService.addintro(uidincookie,intro);
        return Response.makeOKRsp("修改信息成功");
    }



}
