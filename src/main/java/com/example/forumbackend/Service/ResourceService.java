package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.forumbackend.Domain.ForumResource;
import com.example.forumbackend.Domain.Zan;
import com.example.forumbackend.Mapper.ResourceMapper;
import com.example.forumbackend.Mapper.ZanMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ZanService zanService;

    @Autowired
    private UserInfoService userInfoService;

    @Value("${resourcetype.file}")
    private Integer filetype;

    @Value("${resourcetype.artical}")
    private Integer articaltype;

    public void addresource(ForumResource forumResource){
        resourceMapper.insert(forumResource);
    }

    public void replyresource(LocalDateTime time,Integer uid,Integer rid){
        ForumResource forumResource=new ForumResource();
        forumResource.setLastReplyUID(uid);
        forumResource.setLastreplytime(time);
        forumResource.setRID(rid);
        resourceMapper.updateById(forumResource);
    }

    public ForumResource findresourceByrid(Integer rid){
        QueryWrapper<ForumResource> qw=new QueryWrapper<>();
        qw.eq("resource_id",rid);
        return resourceMapper.selectById(rid);
    }

    //为某资源点赞
    public Zan Add_Zan(Integer rid, Integer uid){
        ForumResource resource=findresourceByrid(rid);
        userInfoService.addzan(resource.getUID());
        resourceMapper.addzan(rid);
        return zanService.addzan(rid,uid);
    }

    public void SUB_ZAN(Integer rid, Integer uid){
        ForumResource resource=findresourceByrid(rid);
        userInfoService.subzan(resource.getUID());
        resourceMapper.addzan(rid);
        zanService.DeleteBYRID_UID(rid,uid);
    }

    public Integer getfilecountbyuid(Integer uid){
        QueryWrapper<ForumResource> qw=new QueryWrapper<>();
        qw.eq("resource_user_id",uid).eq("resource_type",filetype);
        return resourceMapper.selectCount(qw);
    }

    public Integer getarticalcountbyuid(Integer uid){
        QueryWrapper<ForumResource> qw=new QueryWrapper<>();
        qw.eq("resource_user_id",uid).eq("resource_type",articaltype);
        return resourceMapper.selectCount(qw);
    }

}
