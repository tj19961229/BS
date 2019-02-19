package com.example.demo.mapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Results({ //2
            @Result(property = "id", column = "id"), //2
            @Result(property = "username", column = "username"),
            @Result(property = "userpwd", column = "userpwd"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "t_o_s", column = "T_o_S")
    })
    @Select("SELECT * FROM user")
    List<User> getall();

    @Insert("INSERT INTO user(username, userpwd,nickname,t_o_s) VALUES (#{username}, #{userpwd}, #{nickname}, #{t_o_s})")
    void insert(User user);
}
