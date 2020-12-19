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

@Data
@ToString
@TableName("Chat")
public class Chat implements Serializable {
    /* CID                  int not null  auto_increment,
   receiveUID           int,
   sendUID              int,
   GroupID              int,
   message              varchar(1000),
   mtype                int,
   CreateTime           datetime,
     */
    @TableId(value = "CID",type = IdType.AUTO)
    private Integer ID;

    @TableField("receiveUID")
    private Integer ReceiveUID;

    @TableField("sendUID")
    private Integer SendUID;

    @TableField("GroupID")
    private Integer GroupID;

    @TableField("message")
    private String  message;

    @TableField("mtype")
    private Integer mtype;

    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("CreateTime")
    private LocalDateTime CreateTime;
}
