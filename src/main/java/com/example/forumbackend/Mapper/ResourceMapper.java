package com.example.forumbackend.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forumbackend.Domain.Normal.ForumResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceMapper extends BaseMapper<ForumResource> {
    public void addzan(@Param("rid") Integer rid);
    public void subzan(@Param("rid") Integer rid);
}
