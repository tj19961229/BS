package com.example.demo.mapper;

import com.example.demo.entity.login;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface loginMapper {

    @Select("SELECT * FROM user where username=#{username} and userpwd=#{userpwd}")
    int logincx(login _login);
}
