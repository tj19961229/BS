package com.example.demo.mapper;

import com.example.demo.entity.User_miniui;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface MiniuserMapper {
    @Select("select * from mini_user where bumen=#{bumen}")
    List<User_miniui> mini_select_bumen(String value);
}
