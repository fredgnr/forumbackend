package com.example.forumbackend.Domain.ChatRoom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@TableName("GroupItem")
@ApiModel(description = "加入群记录")
public class GroupItem implements Serializable {
    /*
      ID                   int not null  auto_increment,
   uid                  int,
   GID                  int,
     */
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer ID;

    @ApiModelProperty(value = "用户UID",example = "0")
    @TableField("uid")
    private Integer UID;

    @ApiModelProperty(value = "群ID",example = "0")
    @TableField("GID")
    private Integer GID;


}
