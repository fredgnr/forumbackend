package com.example.forumbackend.Domain.Normal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@TableName("resources")
public class ForumResource implements Serializable {
    /*
   resource_id          int not null  auto_increment,
   resource_section_id  int,
   resource_user_id     int,
   resource_zan 		int,
   resource_last_reply_uid int,
   resource_type        int,
   resource_created_time datetime,
   resource_price       int,
   resource_last_reply_time datetime,
     */
    @ApiModelProperty(value = "主键",example = "0")
    @TableId(value = "resource_id",type = IdType.AUTO)
    private Integer RID;

    @ApiModelProperty(value = "资源所属的板块",example = "0")
    @TableField("resource_section_id")
    private Integer SectionID;

    @ApiModelProperty(value = "资源上传者的UID",example = "0")
    @TableField("resource_user_id")
    private Integer UID;

    @ApiModelProperty(value="资源获得的赞的数量",example = "0")
    @TableField("resource_zan")
    private Integer Zan;

    @ApiModelProperty(value = "资源最后一个回复者的uid",example = "")
    @TableField("resource_last_reply_uid")
    private Integer LastReplyUID;

    @ApiModelProperty(value = "资源的类型",example = "1")
    @TableField("resource_type")
    private Integer Type;

    @ApiModelProperty(value = "资源的价格",example = "0")
    @TableField("resource_price")
    private Integer Price;

    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "资源上传时间",example = "2020-11-22 12:23:11")
    @TableField("resource_created_time")
    private LocalDateTime Createdtime;

    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后评论时间",example = "")
    @TableField("resource_last_reply_time")
    private LocalDateTime Lastreplytime;
}
