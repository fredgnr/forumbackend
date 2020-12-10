package com.example.forumbackend.Contoller;

import com.example.forumbackend.Domain.ForumResource;
import com.example.forumbackend.Domain.Reply;
import com.example.forumbackend.Service.ReplyService;
import com.example.forumbackend.Service.ResourceService;
import com.example.forumbackend.Utils.CookieUtil;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/reply")
@Api(tags = "回复API(测试完成)")
public class ReplyController {
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private CookieUtil cookieUtil;

    @PostMapping("/replyresource")
    @Transactional
    @ApiOperation(value = "评论资源")
    @ApiResponses({
            @ApiResponse(code = 112,message = "资源不存在"),
            @ApiResponse(code = 102,message = "成功获取")
    })
    public ResponseResult<Reply> replyresource(HttpServletRequest request,
                                               @ApiParam(value = "资源RID") @RequestParam Integer rid,
                                               @ApiParam(value = "评论内容") @RequestParam String content){
        ForumResource resource=resourceService.findresourceByrid(rid);
        System.out.println(resource);
        if(resource==null){
            return Response.makeRsp(ResultCode.RESOURCE_NOT_EXIST.code,"resource_id为"+rid+"的资源不存在");
        }
        replyService.addreply(content,rid,cookieUtil.getuid(request));
        return Response.makeOKRsp();
    }

    @GetMapping("/repliesbyrid")
    @Transactional
    @ApiOperation(value = "获取某资源的评论")
    public ResponseResult<List<Reply>> getrepliesbyrid(
            @RequestParam @ApiParam(value = "资源RID") Integer rid,
            Integer pageindex,
            Integer pagesize){
        return  Response.makeOKRsp(replyService.getrepliesbyrid(rid,pageindex,pagesize));
    }

    @GetMapping("/replycountbyrid")
    @Transactional
    @ApiOperation(value = "获取某资源评论数量")
    public  ResponseResult<Integer> replycountbyrid( @RequestParam @ApiParam(value = "资源RID") Integer rid){
        return Response.makeOKRsp(replyService.getcountbyrid(rid));
    }
}
