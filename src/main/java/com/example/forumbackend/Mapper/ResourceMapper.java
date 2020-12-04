package com.example.forumbackend.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forumbackend.Domain.ForumResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceMapper extends BaseMapper<ForumResource> {
    public void addzan(@Param("rid") Integer rid);
}
