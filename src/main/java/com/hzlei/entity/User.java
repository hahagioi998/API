package com.hzlei.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author hzlei
 * @date 2021/05/06 13:27
 * Description  user
 */
@Data
public class User {

    @NotNull(message = "用户id不能为空")
    private Long id;

    @NotNull(message = "用户手机号不能为空")
    @Size(min = 11, max = 11, message = "用户手机号长度必须是11个数字")
    private String phone;

    @NotNull(message = "用户密码不能为空")
    @Size(min = 6, max = 11, message = "用户密码长度必须是6-16个字符")
    private String password;

    @NotNull(message = "用户邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
}
