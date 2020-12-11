package com.example.forumbackend.Contoller;


import com.example.forumbackend.Domain.User;
import com.example.forumbackend.Domain.User_Info;
import com.example.forumbackend.Service.UserInfoService;
import com.example.forumbackend.Service.UserService;
import com.example.forumbackend.Utils.CookieUtil;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import io.lettuce.core.GeoArgs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Length;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/user")
@Api(tags = "用户信息(测试完成)")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    private final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CookieUtil cookieUtil;

    @Transactional
    @GetMapping("/getuser")
    @ApiOperation(value = "获取自己的账户类(测试完成)")
    public ResponseResult<User> getuser(
             HttpServletRequest request) {
        logger.info("nums of cookies:\t"+request.getCookies().length);
        Integer uidincookie= cookieUtil.getuid(request);
        User user=userService.findByUID(uidincookie);
        return Response.makeOKRsp(user);
    }

    @Transactional
    @GetMapping("/getinfo")
    @ApiOperation(value = "获取User_Info信息(测试完成)")
    public  ResponseResult<User_Info> getuserinfo(HttpServletRequest request){
        Integer uid=cookieUtil.getuid(request);
        User_Info userInfo=userInfoService.findByUID(uid);
        return Response.makeOKRsp(userInfo);
    }

    @Transactional
    @GetMapping("/getuserbyuid")
    @ApiOperation(value = "根据UID获取User,隐藏密码邮箱(测试完成)")
    public ResponseResult<User> getuserbyuid(
            Integer uid) {
        User user=userService.findByUID(uid);
        user.setPassword(null);
        user.setEmail(null);
        return Response.makeOKRsp(user);
    }

    @Transactional
    @GetMapping("/getinfobyuid")
    @ApiOperation(value = "根据uid获取User_Info信息，隐藏余额和infoID(测试完成)")
    public  ResponseResult<User_Info> getuserinfobyuid(Integer uid){
        User_Info userInfo=userInfoService.findByUID(uid);
        userInfo.setUserBalance(null);
        userInfo.setInfoID(null);
        return Response.makeOKRsp(userInfo);
    }


    @Transactional
    @PostMapping("/singup")
    @ApiOperation(value="注册账号(已测试)")
    @ApiResponses({
            @ApiResponse(code=108,message = "账号已被注册"),
            @ApiResponse(code=109,message = "邮箱已被注册"),
            @ApiResponse(code=102,message = "成功注册")
    })
    public ResponseResult<User> singup(@RequestBody User user){
        if(userService.findByAccount(user.getAccount())!=null){
            return Response.makeRsp(
                    ResultCode.ACCOUNT_DUPLICATED.code, user.getAccount()+"账号已被注册");
        }
        else if(userService.findByEmail(user.getEmail())!=null){
            return Response.makeRsp(ResultCode.EMAIL_DUPLICATED.code, user.getEmail()+"邮箱已被注册");
        }
        else{
            userService.insertUser(user);
            User_Info userInfo=new User_Info();
            userInfo.setUserID(user.getUID());
            userInfo.setUserIntro("");
            userInfo.setUserZan(0);
            userInfo.setUserBalance(0);
            userInfo.setUserPoint(0);
            userInfoService.insertinfo(userInfo);
            return Response.makeOKRsp(user);
        }
    }

    @Transactional
    @PutMapping("/changename")
    @ApiOperation(value = "修改姓名，具体账号从cookie中获取")
    public ResponseResult<User> changename(HttpServletRequest request,String name){
        Integer uidincookie= cookieUtil.getuid(request);;
        userService.changeName(uidincookie,name);
        return Response.makeOKRsp();
    }

    @Transactional
    @PutMapping("/changepassword")
    @ApiOperation(value = "修改密码，账号从cookie中获取")
    public ResponseResult<User> changepassword(HttpServletRequest request,String password){
        Integer uidincookie= cookieUtil.getuid(request);;
        userService.changePassword(uidincookie,password);
        return Response.makeOKRsp();
    }

    @Transactional
    @PutMapping("/changeintro")
    @ApiOperation(value = "修改个人简介，账号从cookie中获取")
    public ResponseResult<User_Info> changeintro(HttpServletRequest request,String intro){
        Integer uidincookie= cookieUtil.getuid(request);
        System.out.println("uid:"+ uidincookie.toString()+"\tintro:"+intro);
        userInfoService.addintro(uidincookie,intro);
        return Response.makeOKRsp("修改信息成功");
    }

    @Transactional
    @GetMapping("/getrankbyuid")
    @ApiOperation(value = "获取某用户的排名")
    public ResponseResult<Integer> getrankbyuid(Integer uid){
        User_Info tmp=userInfoService.findByUID(uid);
        if(tmp==null)
            return Response.makeRsp(ResultCode.USER_NOT_EXIST.code, "查询用户不存在");
        List<User_Info> userInfos=userInfoService.findAlldesc();
       /* userInfos.sort(new Comparator<User_Info>() {
            @Override
            public int compare(User_Info o1, User_Info o2) {
                if(o1.getUserPoint()>o2.getUserPoint())
                    return 1;
                else if(o1.getUserPoint().equals(o2.getUserPoint()))
                    return 0;
                else return -1;
            }
        });*/
        int i=0;
        for(i=0;i< userInfos.size();i++){
            if(userInfos.get(i).getUserID().equals(uid))
                return Response.makeOKRsp(i);
        }
        return Response.makeErrRsp("查询失败");
    }

    @Transactional
    @GetMapping("/getranks")
    @ApiOperation(value = "获取排行榜某页所有人的UID")
    public ResponseResult<List<Integer>> getranks(Integer pageindex,Integer pagesize){
        List<User_Info> userInfos=userInfoService.finddescbypage(pageindex,pagesize);
        List<Integer> integerList = new ArrayList<>();
        for(User_Info userInfo:userInfos){
            integerList.add(userInfo.getUserID());
        }
        return Response.makeOKRsp(integerList);
    }
}
