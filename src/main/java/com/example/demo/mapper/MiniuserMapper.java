package com.example.demo.mapper;

import com.example.demo.entity.User_miniui;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.omg.CORBA.INTERNAL;

import java.util.List;
@Mapper
public interface MiniuserMapper {
    @Select("select * from mini_user where bumen=#{bumen}")
    List<User_miniui> mini_select_bumen(String value);

    @Insert("<script>insert into mini_user(" +
            "<if test=\"bumen != null\">bumen</if> " +
            "<if test=\"loginname != null\">,loginname</if>" +
            "<if test=\"name != null\">,name</if>" +
            "<if test=\"gender != 0\">,gender</if>" +
            "<if test=\"age != 0\">,age</if>" +
            "<if test=\"birthday != null\">,birthday</if>)" +
            "values(" +
            "<if test=\"bumen != null\">#{bumen}</if>" +
            "<if test=\"loginname != null\">,#{loginname}</if>" +
            "<if test=\"name != null\">,#{name}</if>" +
            "<if test=\"gender != 0\">,#{gender}</if>" +
            "<if test=\"age != 0\">,#{age}</if>" +
            "<if test=\"birthday != null\">,#{birthday}</if>" +
            ")</script>")
    Integer mini_add(User_miniui user_miniui);

    @Update("update mini_user set loginname=#{loginname},name=#{name},gender=#{gender},age=#{age},birthday=#{birthday},bumen=#{bumen} where id=#{id}")
    Integer mini_update(User_miniui user_miniui);
}
