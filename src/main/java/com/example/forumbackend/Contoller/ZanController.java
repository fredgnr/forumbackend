package com.example.forumbackend.Contoller;

import com.example.forumbackend.Domain.Zan;
import com.example.forumbackend.Service.ResourceService;
import com.example.forumbackend.Service.ZanService;
import com.example.forumbackend.Utils.CookieUtil;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public ResponseResult<Zan> Zan_Resource(HttpServletRequest request,Integer rid){
        Integer uid=cookieUtil.getuid(request);
        if(zanService.findByRID_UID(rid,uid)==null){
            return Response.makeRsp(ResultCode.RESOURCE_ZAN_ALREADY.code, "此资源已被用户此用户点赞");
        }
        Zan zan=resourceService.Add_Zan(rid,uid);
        return Response.makeOKRsp(zan);
    }
}
