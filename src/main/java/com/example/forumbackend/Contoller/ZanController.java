package com.example.forumbackend.Contoller;

import com.example.forumbackend.Domain.ForumResource;
import com.example.forumbackend.Domain.Zan;
import com.example.forumbackend.Service.ResourceService;
import com.example.forumbackend.Service.ZanService;
import com.example.forumbackend.Utils.CookieUtil;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/zan")
public class ZanController {
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ZanService zanService;

    @Autowired
    private CookieUtil cookieUtil;

    @PostMapping("/addzan")
    @ApiOperation(value = "点赞某资源")
    public ResponseResult<Zan> Zan_Resource(HttpServletRequest request,Integer rid){
        Integer uid=cookieUtil.getuid(request);
        ForumResource resource=resourceService.findresourceByrid(rid);
        if(resource==null)
            return Response.makeRsp(ResultCode.RESOURCE_NOT_EXIST.code, "点赞资源不存在");
        if(zanService.findByRID_UID(rid,uid)!=null){
            return Response.makeRsp(ResultCode.RESOURCE_ZAN_ALREADY.code, "此资源已被用户此用户点赞");
        }
        Zan zan=resourceService.Add_Zan(rid,uid);
        return Response.makeOKRsp(zan);
    }

    @GetMapping("/ifzan")
    @ApiOperation(value = "查询用户是否点赞了某资源")
    public ResponseResult<Boolean> if_zann(HttpServletRequest request,Integer rid){
        Integer uid=cookieUtil.getuid(request);
        Zan zan=zanService.findByRID_UID(rid,uid);
        return Response.makeOKRsp(zan != null);
    }

    @GetMapping("/getresourcezan")
    @ApiOperation(value = "获得资源的赞数量")
    public ResponseResult<Integer> getrzan(Integer rid){
        ForumResource forumResource=resourceService.findresourceByrid(rid);
        return Response.makeOKRsp(forumResource==null?0:forumResource.getZan());
    }
}
