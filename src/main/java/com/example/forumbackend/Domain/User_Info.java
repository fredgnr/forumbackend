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
    @ApiModelProperty(value = "info id",example = "0")
    private Integer infoid;

    @TableField("user_id")
    @ApiModelProperty(value = "对应的用户UID",example = "0")
    private Integer UserID;

    @TableField("user_Intro")
    @ApiModelProperty(value = "用户自我介绍",example = "test test")
    private String UserIntro;

    @TableField("user_zan")
    @ApiModelProperty(value = "用户得到的赞的数量",example = "0")
    private Integer UserZan;

    @TableField("user_point")
    @ApiModelProperty(value = "用户经验值",example = "0")
    private Integer UserPoint;

    @TableField("user_balance")
    @ApiModelProperty(value = "用户余额",example = "0")
    private Integer UserBalance;
}
