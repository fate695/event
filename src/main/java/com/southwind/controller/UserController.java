package com.southwind.controller;

import com.southwind.pojo.Result;
import com.southwind.pojo.User;
import com.southwind.pojo.dto.PasswordUpdateDTO;
import com.southwind.pojo.dto.UserRegisterDTO;
import com.southwind.pojo.dto.UserUpdateDTO;
import com.southwind.service.UserService;
import com.southwind.utils.JwtUtil;
import com.southwind.utils.Md5Util;
import com.southwind.utils.ThreadLocalUtil;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
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
        //登录成功，生成并下发JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        String token = JwtUtil.genToken(claims);
        return Result.success(token);
    }

    //获取用户详细信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        //通过令牌解析用户数据
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        User user = userService.getById(id);
        return Result.success(user);
    }

    //更新用户信息
    @PutMapping("/update")
    public Result update(@Valid @RequestBody UserUpdateDTO dto){
        userService.updateById(dto.toUser());
        return Result.success();
    }

    //更新用户头像
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    //更新用户密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@Valid @RequestBody PasswordUpdateDTO dto){
        userService.updatePwd(dto);
        return Result.success();
    }

}
