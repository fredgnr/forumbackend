package com.example.forumbackend.Domain.ChatRoom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@TableName("Chat")
@ApiModel(description = "聊天记录")
public class Chat implements Serializable {
    /* CID                  int not null  auto_increment,
   receiveUID           int,
   sendUID              int,
   GroupID              int,
   message              varchar(1000),
   mtype                int,
   CreateTime           datetime,
     */
    @ApiModelProperty(value = "记录CID",example = "0")
    @TableId(value = "CID",type = IdType.AUTO)
    private Integer ID;

    @TableField("receiveUID")
    @ApiModelProperty(value = "接收者UID",example = "0")
    private Integer ReceiveUID;

    @TableField("sendUID")
    @ApiModelProperty(value = "发送者UID",example = "0")
    private Integer SendUID;

    @TableField("GroupID")
    @ApiModelProperty(value = "群聊发送群GID",example = "0")
    private Integer GroupID;

    @TableField("message")
    @ApiModelProperty(value = "消息内容",example = "message test")
    private String  message;

    @TableField("mtype")
    @ApiModelProperty(value = "消息记录种类(群聊or私聊)",example = "0")
    private Integer mtype;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("createTime")
    @ApiModelProperty(value = "发送时间",example = "2020-12-22 01:35:00")
    private LocalDateTime createTime;
}
