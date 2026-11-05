package com.southwind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.southwind.pojo.User;
import com.southwind.pojo.dto.PasswordUpdateDTO;
import com.southwind.service.UserService;
import com.southwind.mapper.UserMapper;
import com.southwind.utils.Md5Util;
import com.southwind.utils.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    public void updateAvatar(String avatarUrl) {
        // 从令牌中获取当前登录用户 id
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        // MP自带update方法 + LambdaUpdateWrapper，只更新user_pic字段
        update(new LambdaUpdateWrapper<User>()
                .eq(User::getId, id)
                .set(User::getUserPic, avatarUrl));
    }

    @Override
    public void updatePwd(PasswordUpdateDTO dto) {
        // 校验两次新密码是否一致
        if (!dto.getNewPwd().equals(dto.getRePwd())) {
            throw new IllegalArgumentException("两次输入的新密码不一致");
        }
        // 从令牌中获取当前登录用户 id，并查询用户
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        User user = getById(id);
        // 校验原密码是否正确
        if (user == null || !Md5Util.checkPassword(dto.getOldPwd(), user.getPassword())) {
            throw new IllegalArgumentException("原密码错误");
        }
        // 加密新密码并更新
        User updateUser = new User();
        updateUser.setId(id);
        updateUser.setPassword(Md5Util.getMD5String(dto.getNewPwd()));
        updateById(updateUser);
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




