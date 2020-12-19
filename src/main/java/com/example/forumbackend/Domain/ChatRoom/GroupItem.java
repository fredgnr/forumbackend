package com.example.forumbackend.Domain.ChatRoom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@TableName("GroupItem")
public class GroupItem implements Serializable {
    /*
      ID                   int not null  auto_increment,
   uid                  int,
   GID                  int,
     */
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer ID;

    @TableField("uid")
    private Integer UID;

    @TableField("GID")
    private Integer GID;


}
