package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forumbackend.Domain.ChatRoom.Chat;
import com.example.forumbackend.Mapper.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    public Integer getprivatecount(Integer uid){//获取存在私聊的人的数量
        QueryWrapper<Chat> qw=new QueryWrapper<>();
        qw.eq("mtype",PrivateType).eq("receiveUID",uid);
        qw.groupBy("sendUID");
        return chatMapper.selectCount(qw);
    }


    public List<Integer> getprivatechatbytime(Integer uid,Integer pageindex,Integer pagesize){
        QueryWrapper<Chat> qw=new QueryWrapper<>();
        qw.eq("receiveUID",uid);
        qw.eq("mtype",PrivateType);
        qw.select("max(CreateTime) as CreateTime,sendUID");
        qw.groupBy("sendUID");
        qw.orderByDesc("CreateTime");
        Page<Chat> page=new Page<>(pageindex,pagesize);
        chatMapper.selectPage(page,qw);
        List<Chat> tmp=page.getRecords();
        List<Integer> result=new ArrayList<>();
        for(Chat chat:tmp)
            result.add(chat.getSendUID());
        return result;
    }

    public List<Integer> getgroupchatbytime(Integer uid,Integer pageindex,Integer pagesize){
        QueryWrapper<Chat> qw=new QueryWrapper<>();
        qw.eq("receiveUID",uid);
        qw.eq("mtype",GroupType);
        qw.select("max(CreateTime) as CreateTime,GroupID");
        qw.groupBy("GroupID");
        qw.orderByDesc("CreateTime");
        Page<Chat> page=new Page<>(pageindex,pagesize);
        chatMapper.selectPage(page,qw);
        List<Chat> tmp=page.getRecords();
        List<Integer> result=new ArrayList<>();
        for(Chat chat:tmp)
            result.add(chat.getGroupID());
        return result;
    }

    public void insert(Chat chat){
        chatMapper.insert(chat);
    }
}
