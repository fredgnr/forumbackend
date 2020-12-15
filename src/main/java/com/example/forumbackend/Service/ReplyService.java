package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forumbackend.Domain.Reply;
import com.example.forumbackend.Mapper.ReplyMapper;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReplyService {
    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private ResourceService resourceService;

    public void addreply(String content,Integer rid,Integer uid, LocalDateTime localDateTime){
        //System.out.println("time_now:\t"+localDateTime);
        Reply reply=new Reply();
        reply.setUID(uid);
        reply.setRID(rid);
        reply.setContent(content);
        reply.setTime(localDateTime);
        replyMapper.insert(reply);
        resourceService.replyresource(localDateTime,uid,rid);
    }

    public Integer getcountbyrid(Integer rid){
        QueryWrapper<Reply> qw=new QueryWrapper<>();
        qw.eq("reply_resource_id",rid);
        return  replyMapper.selectCount(qw);
    }

    public List<Reply> getrepliesbyrid(Integer rid,Integer pageindex,Integer pagesize){
        QueryWrapper<Reply> qw=new QueryWrapper<>();
        qw.eq("reply_resource_id",rid);
        Page<Reply> page=new Page<>(pageindex,pagesize);
        replyMapper.selectPage(page,qw);
        return page.getRecords();
    }
}
