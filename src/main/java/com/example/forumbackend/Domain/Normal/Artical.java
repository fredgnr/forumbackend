package com.example.forumbackend.Domain.Normal;

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

@Data
@ToString
@TableName("article")
@ApiModel(description = "文章")
public class Artical {

    @TableId(value = "artical_id",type = IdType.AUTO)
    @ApiModelProperty(example = "0")
    private Integer ID;

    @ApiModelProperty(value = "文章对应的资源号",example = "0")
    @TableField("article_resource_id")
    private Integer ResourceID;

    @ApiModelProperty(value = "文章标题",example = "test_title")
    @TableField("article_title")
    private String Title;

    @ApiModelProperty(value = "文章关键词",example = "")
    @TableField("article_keywords")
    private String Keywords;

    @ApiModelProperty(value = "文章介绍",example = "introduction test")
    @TableField("article_intro")
    private String Introduction;

    @ApiModelProperty(value = "文章正文",example = "content test")
    @TableField("article_content")
    private String Content;

    @ApiModelProperty(value = "文章被浏览次数",example = "0")
    @TableField("article_view")
    private Integer View;

    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "资源上传时间",example = "2020-11-22 12:23:11")
    @TableField("created_time")
    private LocalDateTime createdtime;

    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后评论时间",example = "2020-11-22 12:23:11")
    @TableField("last_reply_time")
    private LocalDateTime lastreplytime;

    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    public LocalDateTime getLastreplytime() {
        return lastreplytime;
    }

    public void setLastreplytime(LocalDateTime lastreplytime) {
        this.lastreplytime = lastreplytime;
    }
}
