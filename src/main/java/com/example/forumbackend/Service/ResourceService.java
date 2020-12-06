package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.forumbackend.Domain.ForumResource;
import com.example.forumbackend.Mapper.ResourceMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    public void addresource(ForumResource forumResource){
        resourceMapper.insert(forumResource);
    }

    public void replyresource(LocalDateTime time,Integer uid,Integer rid){
        ForumResource forumResource=new ForumResource();
        forumResource.setResourcelastreplyuid(uid);
        forumResource.setResourcelastreplytime(time);
        forumResource.setResourceID(rid);
        resourceMapper.updateById(forumResource);
    }

    public ForumResource findresourceByrid(Integer rid){
        QueryWrapper<ForumResource> qw=new QueryWrapper<>();
        qw.eq("resource_id",rid);
        return resourceMapper.selectById(rid);
    }
}
