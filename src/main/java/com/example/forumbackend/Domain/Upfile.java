package com.example.forumbackend.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@ApiModel(description = "上传的文件")
@TableName("upfile")
@Data
@ToString
public class Upfile {
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

    @TableField("upfile_resource_id")
    private Integer resourceid;

    @TableField("upfile_path")
    private String path;

    @TableField("upfile_title")
    private String title;

    @TableField("upfile_filename")
    private String filename;

    @TableField("upfile_keywords")
    private String keywords;

    @TableField("upfile_intro")
    private String intro;

}
