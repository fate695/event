package com.southwind.utils;

import java.util.Map;

public class TokenUtils {
    public static Integer getUserId() {
        // 从令牌中获取当前登录用户 id，并查询用户
        Map<String, Object> claims = ThreadLocalUtil.get();
        return (Integer) claims.get("id");
    }
}
