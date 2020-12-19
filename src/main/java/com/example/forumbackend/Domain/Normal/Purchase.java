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
@ApiModel("用户购买记录")
@TableName("purchase")
public class Purchase {
    /*
    purchase_id         int not null  auto_increment,
    purchase_resource_id      int,
    purchase_user_id 				int,
    purchase_price				int,
    purchase_time    			datetime,
     */
    @TableId(value = "purchase_id",type = IdType.AUTO)
    private Integer ID;

    @ApiModelProperty("购买资源RID")
    @TableField("purchase_resource_id")
    private Integer RID;

    @ApiModelProperty("购买者UID")
    @TableField("purchase_user_id")
    private Integer UID;

    @ApiModelProperty("购买价格")
    @TableField("purchase_price")
    private Integer Price;

    @ApiModelProperty("购买时间")
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("purchase_time")
    private LocalDateTime PurchaseTime;
}
