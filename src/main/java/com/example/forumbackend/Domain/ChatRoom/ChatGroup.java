package com.example.forumbackend.Domain.ChatRoom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Example;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Data
@TableName("chatgroup")
@ApiModel(description = "聊天群类")
public class ChatGroup implements Serializable {

    @ApiModelProperty(example = "0", value = "群号")
    @TableId(value = "GID",type = IdType.AUTO)
    private Integer GID;

    @TableField("Groupname")
    @ApiModelProperty(value = "群名",example = "testname")
    private String Name;

    @TableField("Introduce")
    @ApiModelProperty(value = "群介绍",example = "test introduce")
    private String Introduce;

    @TableField("create_time")
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "群创建时间",example = "2020-12-22 01:35:00")
    private LocalDateTime createtime;
}
