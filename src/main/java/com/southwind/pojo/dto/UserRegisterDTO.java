package com.southwind.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegisterDTO {

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^\\S{5,16}$", message = "用户名必须是5-16位非空白字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^\\S{5,16}$", message = "密码必须是5-16位非空白字符")
    private String password;
}
