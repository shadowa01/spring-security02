package com.hz.springsecurity02.service;

import com.hz.springsecurity02.util.MassageJson;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

public interface UserService {
    public MassageJson<String> login(String uname,String upwd);
}
