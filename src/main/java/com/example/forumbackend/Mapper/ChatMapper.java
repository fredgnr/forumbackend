package com.example.forumbackend.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forumbackend.Domain.ChatRoom.Chat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ChatMapper  extends BaseMapper<Chat> {
    List<Chat> selectlatest(@Param("uid") Integer uid);
}
