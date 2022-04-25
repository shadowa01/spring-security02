package com.hz.springsecurity02.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 从这里拿到token 然后把这个token注销
        String token = httpServletRequest.getHeader("utoken");

        redisUtil.del("login:"+token);

        MassageJson<String> massageJson = new MassageJson<>(200,"请求成功","模拟数据");

        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(JSONObject.toJSONString(massageJson));



    }
}
