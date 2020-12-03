package com.example.forumbackend.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forumbackend.Domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
