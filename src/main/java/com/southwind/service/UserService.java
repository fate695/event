package com.southwind.service;

import com.southwind.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.southwind.pojo.dto.PasswordUpdateDTO;
import com.southwind.pojo.dto.UserRegisterDTO;

/**
* @author lpj
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2026-11-02 14:55:41
*/
public interface UserService extends IService<User> {
    //查询用户名是否存在
    boolean findByUserName(String username);
    //根据用户名查询用户信息
    User findUserByUserName(String username);
    //注册用户
    void register(String username, String password);
    //更新用户头像
    void updateAvatar(String avatarUrl);
    //更新用户密码
    void updatePwd(PasswordUpdateDTO dto);
}
