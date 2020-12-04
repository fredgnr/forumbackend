package com.example.forumbackend.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@TableName("reply")
@Data
@ToString
@ApiModel(description = "评论")
public class Reply {
    /*
   reply_id             int not null  auto_increment,
   reply_resource_id    int,
   reply_uid            int,
   reply_content        varchar(2000),
   reply_time           datetime,
     */

    @ApiModelProperty(example = "0")
    @TableId(value = "reply_id",type = IdType.AUTO)
    private Integer ID;

    @ApiModelProperty(value = "评论所回复的资源id",example = "0")
    @TableField("reply_resource_id")
    private Integer RID;

    @ApiModelProperty(value = "评论发出者的UID",example = "0")
    @TableField("reply_uid")
    private Integer UID;

    @ApiModelProperty(value = "评论内容",example = "test")
    @TableField("reply_content")
    private String Content;

    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "评论时间",example = "2020-11-22 12:23:11")
    @TableField("reply_time")
    private LocalDateTime time;

}
