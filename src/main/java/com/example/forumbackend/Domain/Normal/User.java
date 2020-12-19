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
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@TableName("Users")
@ApiModel(description = "用户类")
public class User implements Serializable {
    private static final long serialVersionUID = -1L;

    @TableId(value="uid",type = IdType.AUTO)
    @ApiModelProperty(value = "用户UID",example = "0")
    private Integer UID;

    @TableField(value = "user_account")
    @ApiModelProperty(value = "账号",example = "513317651")
    private String Account;

    @TableField(value = "user_name")
    @ApiModelProperty(value = "用户姓名",example = "fred")
    private String Name;

    @ApiModelProperty(value = "密码",example = "123456789")
    @TableField(value = "user_password")
    private String Password;

    @ApiModelProperty(value = "邮箱",example = "513317651@qq.com")
    @TableField(value="user_email")
    private String email;
}
