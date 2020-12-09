package com.example.forumbackend.Contoller;

import com.example.forumbackend.Domain.ForumResource;
import com.example.forumbackend.Domain.Purchase;
import com.example.forumbackend.Domain.User_Info;
import com.example.forumbackend.Service.PurchaseService;
import com.example.forumbackend.Service.ResourceService;
import com.example.forumbackend.Service.UserInfoService;
import com.example.forumbackend.Utils.CookieUtil;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CookieUtil cookieUtil;

    @PostMapping("/purchase")
    @Transactional
    @ApiOperation(value = "购买资源")
    public ResponseResult<Purchase> pur(HttpServletRequest request,Integer rid){
        Integer uid=cookieUtil.getuid(request);
        ForumResource resource=resourceService.findresourceByrid(rid);
        if(resource==null)
            return Response.makeRsp(ResultCode.RESOURCE_NOT_EXIST.code, "请求资源不存在");
        User_Info info=userInfoService.findByUID(uid);
        if(purchaseService.findByUIDRID(uid,rid)!=null){
            return  Response.makeRsp(ResultCode.RESOURCE_PURCHASED.code, "此资源已购买");
        }
        if(info.getUserBalance()<resource.getPrice()){
            return Response.makeRsp(ResultCode.BALANCE_NOT_ENOUGH.code, "余额不足");
        }
        info.setUserBalance(info.getUserBalance()-resource.getPrice());
        userInfoService.update(info);//修改用户余额
        Purchase purchase=new Purchase();
        purchase.setUID(uid);
        purchase.setRID(rid);
        purchase.setPrice(resource.getPrice());
        purchase.setLocalDateTime(LocalDateTime.now());
        purchaseService.addpur(purchase);
        return Response.makeOKRsp(purchase);
    }

    @GetMapping("/countbyuid")
    @Transactional
    @ApiOperation("获取请求用户购买过的资源数量")
    public ResponseResult<Integer> getcountbyuid(HttpServletRequest request){
        Integer uid=cookieUtil.getuid(request);
        return Response.makeOKRsp(purchaseService.getcountbyuid(uid));
    }

    @GetMapping("/getpurchasesbyuid")
    @Transactional
    @ApiOperation(value = "获取用户的购买记录")
    public ResponseResult<List<Purchase>> getpurchasesbyuid(
            HttpServletRequest request,
            Integer pageindex,
            Integer pagesize){
        Integer uid=cookieUtil.getuid(request);
        return Response.makeOKRsp(purchaseService.getpurchasesbyuid(uid,pageindex,pagesize));
    }

    @GetMapping("/getpurchasesbyrid")
    @Transactional
    @ApiOperation(value = "获取用户发布的某资源的购买记录")
    public ResponseResult<List<Purchase>> getpurchasesbyrid(
            HttpServletRequest request,
            Integer rid,
            Integer pageindex,
            Integer pagesize){
        Integer uid=cookieUtil.getuid(request);
        ForumResource resource=resourceService.findresourceByrid(rid);
        if(resource==null)
            return  Response.makeRsp(ResultCode.RESOURCE_NOT_EXIST.code, "请求资源不存在");
        else if(!resource.getUID().equals(uid))
            return Response.makeRsp(ResultCode.RESOURCE_NOT_BELONGS_TO_YOU.code, "请求资源不属于此用户上传");
        return Response.makeOKRsp(purchaseService.getpurchasesbyuid(uid,pageindex,pagesize));
    }
}
