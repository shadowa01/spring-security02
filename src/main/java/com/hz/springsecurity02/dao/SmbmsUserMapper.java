package com.hz.springsecurity02.dao;

import com.hz.springsecurity02.pojo.SmbmsUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SmbmsUserMapper {

    @Select("select * from smbms_user where userCode=#{uname} and userPassword=#{upwd}")
    public SmbmsUser login(
            @Param("uname")String uname,
            @Param("upwd")String upwd
    );
}
