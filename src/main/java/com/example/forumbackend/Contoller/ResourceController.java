package com.example.forumbackend.Contoller;

import com.example.forumbackend.Domain.Normal.ForumResource;
import com.example.forumbackend.Service.ResourceService;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @GetMapping("/getbyrid")
    public ResponseResult<ForumResource> getbyrid(Integer rid){
        return Response.makeOKRsp(resourceService.findresourceByrid(rid));
    }
}
