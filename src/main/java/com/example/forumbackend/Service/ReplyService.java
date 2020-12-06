package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.forumbackend.Domain.Reply;
import com.example.forumbackend.Mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReplyService {
    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private ResourceService resourceService;

    public void addreply(String content,Integer rid,Integer uid){
        LocalDateTime localDateTime=LocalDateTime.now();
        //System.out.println("time_now:\t"+localDateTime);
        Reply reply=new Reply();
        reply.setUID(uid);
        reply.setRID(rid);
        reply.setContent(content);
        reply.setTime(localDateTime);
        replyMapper.insert(reply);
        resourceService.replyresource(localDateTime,uid,rid);
    }
}
