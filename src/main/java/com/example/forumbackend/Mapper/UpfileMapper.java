package com.example.forumbackend.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forumbackend.Domain.Upfile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UpfileMapper extends BaseMapper<Upfile> {
    public void add_purchasetime(@Param("rid") Integer rid);
}
