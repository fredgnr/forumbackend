package com.example.forumbackend.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forumbackend.Domain.Normal.User_Info;
import com.example.forumbackend.Utils.cacheUtils.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@CacheNamespace(implementation = MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface UserInfoMapper extends BaseMapper<User_Info> {

    public void addzan(@Param("uid") Integer uid);
    public void subzan(@Param("uid") Integer uid);

    public void addpoint(@Param("extra") Integer extra,@Param("uid") Integer uid);

    public void addbalance(@Param("extra") Integer extra,@Param("uid") Integer uid);

    public void addpointbyrid(@Param("newpoint") Integer point,@Param("rid") Integer rid);
}
