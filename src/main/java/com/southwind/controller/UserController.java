package com.southwind.controller;

import com.southwind.pojo.Result;
import com.southwind.pojo.User;
import com.southwind.pojo.dto.UserRegisterDTO;
import com.southwind.service.UserService;
import com.southwind.utils.Md5Util;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //注册
    @PostMapping("/register")
    public Result register(@Valid UserRegisterDTO dto) {
        //查询用户名是否存在
        //用户名存在，返回错误信息
        if (userService.findByUserName(dto.getUsername())) {
            return Result.error("用户名已存在");
        }
        //用户名不存在，进行注册
        userService.register(dto.getUsername(), dto.getPassword());
        return Result.success();
    }

    //登录
    @PostMapping("/login")
    public Result login(@Valid UserRegisterDTO dto){
        //根据用户名查询用户
        User user = userService.findUserByUserName(dto.getUsername());
        //用户不存在 或 密码不匹配，统一提示，避免暴露"用户名是否存在"
        if (user == null || !Md5Util.checkPassword(dto.getPassword(), user.getPassword())) {
            return Result.error("用户名或密码错误");
        }
        return Result.success("令牌获取");
    }
}
