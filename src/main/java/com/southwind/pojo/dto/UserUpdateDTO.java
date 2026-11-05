package com.southwind.pojo.dto;

import com.southwind.pojo.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserUpdateDTO {
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^\\S{5,16}$", message = "用户名必须是5-16位非空白字符")
    private String username;

    /** 昵称：1~10位非空字符 */
    @NotBlank(message = "昵称不能为空")
    @Pattern(regexp = "^\\S{1,10}$", message = "昵称必须是1~10位非空字符")
    private String nickname;

    /** 邮箱 */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setEmail(email);
        return user;
    }
}
