package com.hz.springsecurity02.service.impl;

import com.hz.springsecurity02.dao.SmbmsUserMapper;
import com.hz.springsecurity02.pojo.SmbmsUser;
import com.hz.springsecurity02.service.UserService;
import com.hz.springsecurity02.util.MassageJson;
import com.hz.springsecurity02.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceimpl implements UserService{

    @Autowired
    private SmbmsUserMapper smbmsUserMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public MassageJson<String> login(String uname, String upwd) {
          /*   String pwd = "";
        try {
            pwd =  MD5.getEncryptedPwd(upwd);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        //调用dao层 获得登录对象
        SmbmsUser smbmsUser = smbmsUserMapper.login(uname,upwd);

        if (smbmsUser!=null){
             /*
                1. 获得时间戳
                2. UUID
                3.JWT

                4.sha256   md5    123456
                5.加密+加盐
             */

             String token = UUID.randomUUID().toString().replace("-","");
             redisUtil.setStrJson("login:"+token,smbmsUser,null);
             return new MassageJson<String>(200,"登陆成功",token);
        }else {
            //登录失败
            return new MassageJson<String>(250,"登录失败",null);
        }
    }
}
