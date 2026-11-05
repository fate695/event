package com.southwind.interceptors;

import com.southwind.utils.JwtUtil;
import com.southwind.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //放行 OPTIONS 预检请求（如前端跨域需要）
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        //从请求头中获取token
        String token = request.getHeader("Authorization");
        //token不存在或为空，返回401
        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            return false;
        }
        //验证token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //验证通过，把业务数据存入ThreadLocal供后续使用
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            //token过期或被篡改
            response.setStatus(401);
            return false;
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //清除ThreadLocal
        ThreadLocalUtil.remove();
    }
}
