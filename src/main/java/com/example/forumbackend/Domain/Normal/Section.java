package com.example.forumbackend.Domain.Normal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("section")
@ApiModel("板块")
public class Section implements Serializable {
    /*
    section_id           int not null  auto_increment,
   section_name         varchar(40),
   section_count        int,
     */
    @TableId(value = "section_id",type = IdType.AUTO)
    @ApiModelProperty(example = "0")
    private Integer SectionID;

    @TableField("section_name")
    @ApiModelProperty(value = "板块名",example = "JAVA")
    private String SectionName;

    @TableField("section_count")
    @ApiModelProperty(value = "板块资源数量",example = "0")
    private Integer SectionCount;
}
