package com.example.forumbackend.Contoller;

import com.example.forumbackend.Domain.Role;
import com.example.forumbackend.Domain.User;
import com.example.forumbackend.Service.LoginService;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;


    @Value("${PasswordWrong}")
    private  static String PasswordWrong;

    @Value("${AccountNotExist}")
    static public String AccountNotExist;

    @Value("${cookie.timeout}")
    private Integer cookie_timeout;


    @GetMapping("/in")
    @ApiOperation(value = "登录")
    @Transactional
    public ResponseResult<User> logintest(HttpServletResponse response, String account, String password){
        Role role=new Role();
        String token=loginService.login(account,password,role);
        if(token.equals(AccountNotExist)){
            return Response.makeRsp(ResultCode.ACCOUNTNOTEXIST.code,"账号不存在");
        }
        else if(token.equals(PasswordWrong)){
            return Response.makeRsp(ResultCode.PASSWORDWRONG.code, "密码错误");
        }
        else{
            Cookie cookie=new Cookie("token",token);
            cookie.setPath("/");
            cookie.setMaxAge(cookie_timeout*60);
            response.addCookie(cookie);
            Cookie cookie1=new Cookie("UID",role.getUser().getUID().toString());
            cookie1.setMaxAge(cookie_timeout*60);
            cookie1.setPath("/");
            response.addCookie(cookie1);
            return Response.makeOKRsp(role.getUser());
        }
    }


    @GetMapping("/out")
    @ApiOperation("登出")
    public ResponseResult<User> logout(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("UID")) {
                loginService.logout(new Integer(cookie.getValue()));
                return Response.makeRsp(ResultCode.LOGOUT_SUCCESS.code, "成功登出");
            }
        }
        return Response.makeRsp(ResultCode.LOGOUT_FAILED.code, "登出失败");
    }
}
