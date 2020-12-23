package com.example.forumbackend.Domain.ChatRoom;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@TableName("ChatItem")
@ApiModel(description = "发生聊天关系（群聊or私聊）的记录")
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

    @ApiModelProperty(value = "群ID",example = "0")
    @TableField("GID")
    private Integer GID;

    @ApiModelProperty(value = "消息接收方",example = "0")
    @TableField("UID1")
    private Integer UID1;//接收方

    @ApiModelProperty(value = "消息发送方",example = "0")
    @TableField("UID2")
    private Integer UID2;

    @ApiModelProperty(value = "关系种类（群聊or私聊）",example = "0")
    @TableField("mtype")
    private Integer mtype;

    @ApiModelProperty(value = "发生时间",example = "2020-12-23 16:45:00")
    @TableField("happentime")
   @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime happentime;

    @TableLogic
    @TableField("logic")
    @ApiModelProperty(hidden = true)
    private Integer logic;
}
