package com.southwind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.southwind.pojo.User;
import com.southwind.service.UserService;
import com.southwind.mapper.UserMapper;
import com.southwind.utils.Md5Util;
import org.springframework.stereotype.Service;

/**
* @author lpj
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2026-11-02 14:55:41
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Override
    public void register(String username, String password) {

        //加密
        String md5String = Md5Util.getMD5String(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(md5String);
        // MP自带save方法，直接插入
        save(user);
    }

    @Override
    public boolean findByUserName(String username) {
        // 根据用户名查询用户
        return baseMapper.exists(
                new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, username)
        );
    }

    @Override
    public User findUserByUserName(String username) {
        // 用一个用户对象接收查询结果，查不到返回 null
        return baseMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, username)
        );
    }
}




