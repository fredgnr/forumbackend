package com.example.forumbackend.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forumbackend.Domain.User_Info;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper extends BaseMapper<User_Info> {

    public void addzan(@Param("uid") Integer uid);
    public void subzan(@Param("uid") Integer uid);

    public void addpoint(@Param("extra") Integer extra,@Param("uid") Integer uid);

    public void addbalance(@Param("extra") Integer extra,@Param("uid") Integer uid);

    public void addpointbyrid(@Param("newpoint") Integer point,@Param("rid") Integer rid);
}
