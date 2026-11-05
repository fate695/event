package com.southwind.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PasswordUpdateDTO {

    /** 原密码 */
    @NotBlank(message = "原密码不能为空")
    private String oldPwd;

    /** 新密码 */
    @NotBlank(message = "新密码不能为空")
    @Pattern(regexp = "^\\S{5,16}$", message = "新密码必须是5-16位非空白字符")
    private String newPwd;

    /** 确认新密码 */
    @NotBlank(message = "确认新密码不能为空")
    private String rePwd;
}
