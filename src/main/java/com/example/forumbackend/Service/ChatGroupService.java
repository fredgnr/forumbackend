package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forumbackend.Domain.ChatRoom.ChatGroup;
import com.example.forumbackend.Domain.ChatRoom.GroupItem;
import com.example.forumbackend.Mapper.ChatGroupMapper;
import com.example.forumbackend.Mapper.GroupItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatGroupService {
    @Autowired
    private ChatGroupMapper chatGroupMapper;

    @Autowired
    private GroupItemMapper groupItemMapper;

    public void  CreateGroup(ChatGroup chatGroup){
        chatGroupMapper.insert(chatGroup);
    }

    public ChatGroup findByGID(Integer gid){
        QueryWrapper<ChatGroup> qw=new QueryWrapper<>();
        qw.eq("GID",gid);
        return chatGroupMapper.selectOne(qw);
    }

    public void AddGroup(Integer GID,Integer UID){
        QueryWrapper<GroupItem> qw=new QueryWrapper<>();
        qw.eq("uid",UID);
        qw.eq("GID",GID);
        GroupItem gi=groupItemMapper.selectOne(qw);
        if(gi==null){
            gi=new GroupItem();
            gi.setUID(UID);
            gi.setGID(GID);
            groupItemMapper.insert(gi);
        }
    }

    public List<GroupItem> getlist(Integer gid){
        QueryWrapper<GroupItem> qw=new QueryWrapper<>();
        qw.eq("GID",gid);
        return groupItemMapper.selectList(qw);
    }

    public Boolean verify(Integer gid,Integer uid){//验证某人是否加群
        QueryWrapper<GroupItem> qw=new QueryWrapper<>();
        qw.eq("uid",uid);
        qw.eq("GID",gid);
        return groupItemMapper.selectCount(qw) > 0;
    }

    public ChatGroup search(Integer gid){
        return chatGroupMapper.selectById(gid);
    }

    public List<ChatGroup> search(String str,Integer pageindex,Integer pagesize){
        QueryWrapper<ChatGroup> qw=new QueryWrapper<>();
        qw.like("Introduce",str).or().like("Groupname",str);
        Page<ChatGroup> page=new Page<>(pageindex,pagesize);
        chatGroupMapper.selectPage(page,qw);
        return page.getRecords();
    }
}
