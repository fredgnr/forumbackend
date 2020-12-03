package com.example.forumbackend.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@TableName("user_info")
@Data
@ToString
@EqualsAndHashCode
@ApiModel(description = "用户额外信息")
public class User_Info implements Serializable {
    /*
    info_id              int not null  auto_increment,
    user_id              int,
    user_intro           varchar(500),
    user_zan             int,
    user_point           int,
    user_balance         int,
     */
    @TableId(value = "info_id",type = IdType.AUTO)
    @ApiModelProperty("info id")
    private Integer info_id;

    @TableField("user_id")
    @ApiModelProperty("对应的用户UID")
    private Integer User_ID;

    @TableField("user_Intro")
    @ApiModelProperty("用户自我介绍")
    private String User_Intro;

    @TableField("user_zan")
    @ApiModelProperty("用户得到的赞的数量")
    private Integer User_Zan;

    @TableField("user_point")
    @ApiModelProperty("用户经验值")
    private Integer User_Point;

    @TableField("user_balance")
    @ApiModelProperty("用户余额")
    private Integer User_Balance;
}
