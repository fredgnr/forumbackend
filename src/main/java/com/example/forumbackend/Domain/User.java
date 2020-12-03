package com.example.forumbackend.Domain;

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
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@TableName("Users")
@ApiModel(description = "用户类")
public class User implements Serializable {
    private static final long serialVersionUID = -1L;

    @TableId(value="uid",type = IdType.AUTO)
    @ApiModelProperty("用户UID")
    private Integer UID;

    @TableField(value = "user_account")
    @ApiModelProperty("账号")
    private String Account;

    @TableField(value = "user_name")
    @ApiModelProperty("用户姓名")
    private String Name;

    @ApiModelProperty("密码")
    @TableField(value = "user_password")
    private String Password;

    @ApiModelProperty("邮箱")
    @TableField(value="user_email")
    private String email;
}
