package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forumbackend.Domain.ChatRoom.Chat;
import com.example.forumbackend.Domain.ChatRoom.ChatItem;
import com.example.forumbackend.Domain.ChatRoom.GroupItem;
import com.example.forumbackend.Mapper.ChatItemMapper;
import com.example.forumbackend.Mapper.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatMapper chatMapper;

    @Value("${chatype.group}")
    private Integer GroupType;

    @Value("${chatype.private}")
    private Integer PrivateType;

    @Autowired
    private ChatItemMapper chatItemMapper;

    @Autowired
    private ChatGroupService chatGroupService;

    private void deleteprivateitem(Integer uid1,Integer uid2){
        QueryWrapper<ChatItem> qw=new QueryWrapper<>();
        qw.eq("UID1",uid1);
        qw.eq("UID2",uid2);
        qw.eq("mtype",PrivateType);
        chatItemMapper.delete(qw);
    }

    private void deletegroupitem(Integer uid,Integer GID){
        QueryWrapper<ChatItem> qw=new QueryWrapper<>();
        qw.eq("UID1",uid);
        qw.eq("GID",GID);
        qw.eq("mtype",GroupType);
        chatItemMapper.delete(qw);
    }

    public Integer getprivatecount(Integer uid){//获取存在私聊的人的数量
        QueryWrapper<ChatItem> qw=new QueryWrapper<>();
        qw.eq("mtype",PrivateType).eq("UID1",uid);
        return chatItemMapper.selectCount(qw);
    }


    public List<Integer> getprivatechatbytime(Integer uid,Integer pageindex,Integer pagesize){
        QueryWrapper<ChatItem> qw=new QueryWrapper<>();
        qw.eq("UID1",uid);
        qw.eq("mtype",PrivateType);
        qw.orderByDesc("ID");
        Page<ChatItem> page=new Page<>(pageindex,pagesize);
        chatItemMapper.selectPage(page,qw);
        List<ChatItem> chatItems=page.getRecords();
        List<Integer> result=new ArrayList<>();
        for(ChatItem item:chatItems)
            result.add(item.getUID2());
        return result;
    }

    public List<Integer> getgroupchatbytime(Integer uid,Integer pageindex,Integer pagesize){
        QueryWrapper<ChatItem> qw=new QueryWrapper<>();
        qw.eq("mtype",GroupType);
        qw.eq("UID1",uid);
        qw.orderByDesc("ID");
        Page<ChatItem> page=new Page<>(pageindex,pagesize);
        chatItemMapper.selectPage(page,qw);
        List<ChatItem> chatItems=page.getRecords();
        List<Integer> result=new ArrayList<>();
        for(ChatItem chatItem:chatItems)
            result.add(chatItem.getGID());
        return result;
    }

    public void insert(Chat chat){
        chatMapper.insert(chat);
        if(chat.getMtype().equals(PrivateType)){
            ChatItem item=new ChatItem();
            item.setMtype(PrivateType);
            item.setHappentime(chat.getCreateTime());
            deleteprivateitem(chat.getReceiveUID(), chat.getSendUID());
            deleteprivateitem(chat.getSendUID(),chat.getReceiveUID());
            item.setUID1(chat.getSendUID());
            item.setUID2(chat.getReceiveUID());
            chatItemMapper.insert(item);
            item.setUID2(chat.getSendUID());
            item.setUID1(chat.getReceiveUID());
            chatItemMapper.insert(item);
        }
        else if(chat.getMtype().equals(GroupType)){
            List<GroupItem> groupItems=chatGroupService.getlist(chat.getGroupID());
            for (GroupItem item:groupItems){
                ChatItem chatItem=new ChatItem();
                chatItem.setUID1(item.getUID());
                chatItem.setMtype(GroupType);
                chatItem.setGID(item.getGID());
                chatItem.setHappentime(chat.getCreateTime());
                chatItemMapper.insert(chatItem);
                deletegroupitem(item.getUID(),item.getGID());
            }
        }
    }

    public List<Chat> getprivatechat(Integer uid, Integer senduid, Integer pagesize){
        Page<Chat> page=new Page<>(0,pagesize);
        QueryWrapper<Chat> qw=new QueryWrapper<>();
        qw.eq("mtype",PrivateType);
        qw.nested(i->i.eq("receiveUID",uid).eq("sendUID",senduid))
                .or(i->i.eq("receiveUID",senduid).eq("sendUID",uid));
        qw.orderByDesc("CID");
        chatMapper.selectPage(page,qw);
        return page.getRecords();
    }

    public List<Chat> getgroupchat(Integer gid, Integer pagesize){
        Page<Chat> page=new Page<>(0,pagesize);
        QueryWrapper<Chat> qw=new QueryWrapper<>();
        qw.eq("mtype",PrivateType);
        qw.eq("GroupID",gid);
        qw.orderByDesc("CID");
        chatMapper.selectPage(page,qw);
        return page.getRecords();
    }

    public List<Chat> getgroupchat2(Integer gid,Integer cid, Integer pagesize){
        Page<Chat> page=new Page<>(0,pagesize);
        QueryWrapper<Chat> qw=new QueryWrapper<>();
        qw.eq("mtype",PrivateType);
        qw.eq("GroupID",gid);
        qw.lt("CID",cid);
        qw.orderByDesc("CID");
        chatMapper.selectPage(page,qw);
        return page.getRecords();
    }

    public List<Chat> getprivatechat2(Integer uid,
            Integer senduid,Integer CID,Integer size){
        Page<Chat> page=new Page<>(0,size);
        QueryWrapper<Chat> qw=new QueryWrapper<>();
        qw.eq("mtype",PrivateType);
        qw.lt("CID",CID);
        qw.nested(i->i.eq("receiveUID",uid).eq("sendUID",senduid))
                .or(i->i.eq("receiveUID",senduid).eq("sendUID",uid));
        qw.orderByDesc("CID");
        chatMapper.selectPage(page,qw);
        return page.getRecords();
    }
}
