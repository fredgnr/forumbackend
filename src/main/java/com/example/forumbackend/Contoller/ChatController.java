package com.example.forumbackend.Contoller;

import com.example.forumbackend.Domain.ChatRoom.ChatGroup;
import com.example.forumbackend.Domain.ChatRoom.GroupItem;
import com.example.forumbackend.Service.ChatGroupService;
import com.example.forumbackend.Utils.ChatRoomWebSocketHandler;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatRoomWebSocketHandler handler;

    @Autowired
    private ChatGroupService chatGroupService;

    @PostMapping("{sendID}/to/{receiveID}")
    public void sendToUser (@PathVariable("sendID") Integer sendID,
                            @PathVariable("receiveID") Integer receiveID,String message){
        System.out.println("sendid:\t"+sendID+"\treceiveID:\t"+receiveID+"\tmessage\t"+message);
        if(sendID!=null&&receiveID!=null)
            handler.sendMessageToUser(receiveID,new TextMessage(message));
    }


    @PostMapping("/CreateGroup")
    @Transactional
    public ResponseResult<ChatGroup> CreateGroup(String name, String introduce){
        LocalDateTime now=LocalDateTime.now();
        ChatGroup group=new ChatGroup();
        group.setName(name);
        group.setIntroduce(introduce);
        group.setCreatetime(now);
        chatGroupService.CreateGroup(group);
        return Response.makeOKRsp(group);
    }

    @PostMapping("/addgroup")
    @Transactional
    public ResponseResult<Boolean> AddGroup(@CookieValue("UID") Integer uid,Integer GID){
        ChatGroup group=chatGroupService.findByGID(GID);
        if(group==null){
            return Response.makeRsp(ResultCode.GROUP_NOT_EXIST.code, "群不存在",false);
        }
        chatGroupService.AddGroup(GID,uid);
        return Response.makeOKRsp(true);
    }

    @GetMapping("/getgroupitemlist")
    @Transactional
    public ResponseResult<List<GroupItem>> getlist(Integer GID){
        return Response.makeOKRsp(chatGroupService.getlist(GID));
    }
}
