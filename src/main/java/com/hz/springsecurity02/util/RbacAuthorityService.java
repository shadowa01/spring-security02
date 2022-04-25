package com.hz.springsecurity02.util;

import com.hz.springsecurity02.pojo.SmbmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component("rbacAuthorityService")
public class RbacAuthorityService {
    @Autowired
    private RedisUtil redisUtil;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        // 获取当前请求的URI
        String requestURI = request.getServletPath();
        // 放开登录url
        if (requestURI.equals("/user/login")){
            return true;
        }


        String token = request.getHeader("utoken");
        if(token==null){
            request.setAttribute("code","1");
            return  false;
        }
        //到redis中找对象
        Object obj = redisUtil
                .getStrJson("login:"+token,SmbmsUser.class);
        if(obj==null){
            request.setAttribute("code","1");
            return  false;
        }

        SmbmsUser smbmsUser = (SmbmsUser)obj;
        /*
            1.启动项目是获得所有角色ID 与对应权限  存入redis
            2.根据角色ID  获得相应权限菜单集合 (控制到按钮)

            3.控制菜单  写controller路径




         */


// 权限判断 利用token获取用户登录对象 获取角色ID 通过角色ID到redis中找角色对应 的权限集合
        List<String> roles = new ArrayList<String>();
        roles.add("/user");   //requestURI   /user/adduser
        if (!roles.contains(requestURI)){
            request.setAttribute("code","2");
            return false;
        }
        return  true;


    }





}
