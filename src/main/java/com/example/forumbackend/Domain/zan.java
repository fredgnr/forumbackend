package com.example.forumbackend.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("zan")
@ApiModel(description = "赞")
public class zan {
    /*
    zan_id               int not null  auto_increment,
   zan_resource_id      int,
   zan_uid              int,
   zan_status           int,
     */
    @ApiModelProperty(example = "0")
    @TableId(value = "zan_id",type = IdType.AUTO)
    private Integer ID;

    @ApiModelProperty(example = "0")
    @TableField("zan_resource_id")
    private Integer RID;

    @ApiModelProperty(example = "0")
    @TableField("zan_uid")
    private Integer UID;

    @ApiModelProperty(example = "0")
    @TableField("zan_status")
    private Integer Status;
}
