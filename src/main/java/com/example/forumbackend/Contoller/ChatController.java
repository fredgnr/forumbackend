package com.example.forumbackend.Contoller;

import com.example.forumbackend.Domain.ChatRoom.Chat;
import com.example.forumbackend.Domain.ChatRoom.ChatGroup;
import com.example.forumbackend.Domain.ChatRoom.GroupItem;
import com.example.forumbackend.Service.ChatGroupService;
import com.example.forumbackend.Service.ChatService;
import com.example.forumbackend.Service.UserService;
import com.example.forumbackend.Utils.ChatRoomWebSocketHandler;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private JsonMapper jsonMapper;

    @Value("${chatype.group}")
    private Integer GroupType;

    @Value("${chatype.private}")
    private Integer PrivateType;


    @PostMapping("{sendID}/to/{receiveID}")
    public void sendToUser (@PathVariable("sendID") Integer sendID,
                            @PathVariable("receiveID") Integer receiveID,String message){
        System.out.println("sendid:\t"+sendID+"\treceiveID:\t"+receiveID+"\tmessage\t"+message);
        if(sendID!=null&&receiveID!=null)
            handler.sendMessageToUser(receiveID,new TextMessage(message));
    }

    @PostMapping("/sendprivate")
    @Transactional
    public ResponseResult<Chat> sendprivate(@CookieValue("UID") Integer uid, Integer receivedid,String message) throws JsonProcessingException {
        if(uid.equals(receivedid))
            return Response.makeErrRsp("不能直接给自己发");
        Chat chat=new Chat();
        chat.setCreateTime(LocalDateTime.now());
        chat.setReceiveUID(receivedid);
        chat.setSendUID(uid);
        chat.setMessage(message);
        chat.setMtype(PrivateType);
        if(userService.findByUID(receivedid)==null)
            return Response.makeRsp(ResultCode.USER_NOT_EXIST.code, "用户不存在");
        chatService.insert(chat);
        handler.sendMessageToUser(receivedid,new TextMessage(jsonMapper.writeValueAsString(chat)));
        return Response.makeOKRsp(chat);
    }

    @PostMapping("/sendgroup")
    @Transactional
    public ResponseResult<Chat> sendgroup(@CookieValue("UID") Integer uid, Integer GID,String message) throws JsonProcessingException {
        Chat chat=new Chat();
        chat.setCreateTime(LocalDateTime.now());
        chat.setGroupID(GID);
        chat.setSendUID(uid);
        chat.setMessage(message);
        chat.setMtype(GroupType);
        if(!chatGroupService.verify(GID,uid))
            return Response.makeRsp(ResultCode.NOT_JOIN_GROUP.code, "未加群");
        chatService.insert(chat);
        List<GroupItem> list=chatGroupService.getlist(GID);
        for(GroupItem item:list) {
            if(item.getUID().equals(uid))
                continue;
            handler.sendMessageToUser(item.getUID(), new TextMessage(jsonMapper.writeValueAsString(chat)));
        }
        return Response.makeOKRsp(chat);
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

    @GetMapping("/getprivatecount")
    @Transactional
    public  ResponseResult<Integer> getprivatecount(@CookieValue("UID") Integer uid){
        return Response.makeOKRsp(chatService.getprivatecount(uid));
    }

    @GetMapping("/getprivatechatbytime")
    @Transactional
    public ResponseResult<List<Integer>> getprivatechatbytime(@CookieValue("UID")Integer uid,Integer pageindex,Integer pagesize){
        return Response.makeOKRsp(chatService.getprivatechatbytime(uid,pageindex,pagesize));
    }

    @GetMapping("/getgroupchatbytime")
    @Transactional
    public ResponseResult<List<Integer>> getgroupchatbytime(@CookieValue("UID")Integer uid,Integer pageindex,Integer pagesize){
        return Response.makeOKRsp(chatService.getgroupchatbytime(uid,pageindex,pagesize));
    }


}
