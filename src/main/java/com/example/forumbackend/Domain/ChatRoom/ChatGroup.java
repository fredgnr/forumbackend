package com.example.forumbackend.Domain.ChatRoom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Data
@TableName("chatgroup")
public class ChatGroup implements Serializable {

    @TableId(value = "GID",type = IdType.AUTO)
    private Integer GID;

    @TableField("Groupname")
    private String Name;

    @TableField("Introduce")
    private String Introduce;

    @TableField("create_time")
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createtime;
}
