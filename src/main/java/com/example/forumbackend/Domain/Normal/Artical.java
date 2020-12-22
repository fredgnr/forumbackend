package com.example.forumbackend.Domain.Normal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@TableName("article")
@ApiModel(description = "文章")
public class Artical {
    /*
   artical_id          int  auto_increment not null,
   article_resource_id  int  not null,
   article_title        varchar(200) not null,
   article_keywords     varchar(100) ,
   article_intro        varchar(300) not null,
   article_content      longtext not null,
   article_view         int not null,
     */
    @ApiModelProperty(example = "0")
    @TableId("artical_id")
    private Integer ID;

    @ApiModelProperty(example = "0")
    @TableField("article_resource_id")
    private Integer ResourceID;

    @ApiModelProperty(example = "test_title")
    @TableField("article_title")
    private String Title;

    @ApiModelProperty(example = "")
    @TableField("article_keywords")
    private String Keywords;

    @ApiModelProperty(example = "introduction test")
    @TableField("article_intro")
    private String Introduction;

    @ApiModelProperty(example = "content test")
    @TableField("article_content")
    private String Content;

    @ApiModelProperty(example = "0")
    @TableField("article_view")
    private Integer View;

    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "资源上传时间",example = "2020-11-22 12:23:11")
    @TableField("created_time")
    private LocalDateTime Createdtime;

    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后评论时间",example = "2020-11-22 12:23:11")
    @TableField("last_reply_time")
    private LocalDateTime Lastreplytime;

}
