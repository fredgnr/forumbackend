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

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(description = "上传的文件")
@TableName("upfile")
@Data
@ToString
public class Upfile implements Serializable {
    /*
   upfile_id            int not null  auto_increment,
   upfile_resource_id   int,
   upfile_path          varchar(200),
   upfile_title         varchar(200),
   upfile_filename      varchar(200),
   upfile_keywords      varchar(200),
   upfile_intro         varchar(300),
     */

    @TableId(value = "upfile_id",type = IdType.AUTO)
    private Integer fileid;


    @ApiModelProperty(value = "文件的资源ID",example = "0")
    @TableField("upfile_resource_id")
    private Integer resourceid;


    @ApiModelProperty(value = "文件在服务器上的存储路径",example = "E:\\files",hidden = true)
    @TableField("upfile_path")
    private String path;

    @ApiModelProperty(value = "文件标题",example = "test title")
    @TableField("upfile_title")
    private String title;

    @ApiModelProperty(value = "文件名",example = "test.zip")
    @TableField("upfile_filename")
    private String filename;

    @ApiModelProperty(value = "文件关键词",example = "java")
    @TableField("upfile_keywords")
    private String keywords;

    @ApiModelProperty(value = "文件介绍",example = "test intro")
    @TableField("upfile_intro")
    private String intro;

    @ApiModelProperty(value = "文件被购买次数",example = "0")
    @TableField("purchase_time")
    private int purchasetime;

    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "资源上传时间",example = "2020-11-22 12:23:11",hidden = true)
    @TableField("created_time")
    private LocalDateTime createdtime;

    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }
}
