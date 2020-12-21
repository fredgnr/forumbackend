package com.example.forumbackend.Domain.ChatRoom;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@TableName("ChatItem")
public class ChatItem {
    /*
    ID                   int not null,
   GID                  int,
   UID1                 int,
   UID2                 int,
   mtype                int,
   happentime           datetime,
     */
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer ID;
    @TableField("GID")
    private Integer GID;
    @TableField("UID1")
    private Integer UID1;//接收方
    @TableField("UID2")
    private Integer UID2;
    @TableField("mtype")
    private Integer mtype;
    @TableField("happentime")
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime happentime;

    @TableLogic
    @TableField("logic")
    private Integer logic;
}
