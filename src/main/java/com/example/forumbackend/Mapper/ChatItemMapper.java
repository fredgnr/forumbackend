package com.example.forumbackend.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forumbackend.Domain.ChatRoom.ChatItem;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatItemMapper extends BaseMapper<ChatItem> {
}
