package com.example.demo.mapper;

import com.example.demo.entity.Sysuser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SysuserMapper {

    @Select("select *" +
            "from sys_user " +
            "where 1=1 and number=#{username} " )
    @Results(id="sys_user", value={ //2
            @Result(property = "id", column = "id"), //2
            @Result(property = "username", column = "number"),
            @Result(property = "password", column = "password"),
            @Result(property = "name", column = "name"),
            @Result(property = "BJ", column = "class"),
            @Result(property = "roles", column = "role"),
    })
    Sysuser user_select(Sysuser sysuser);

    @Select("select *" +
            "from sys_user ")
    @ResultMap(value="sys_user")
    List<Sysuser> users_select();

    @Update("<script> UPDATE sys_user SET " +
            "<if test=\"name != null\">name=#{name}</if> " +
            "<if test=\"BJ != null\">class=#{BJ}</if> " +
            "<if test=\"roles != 0\">role=#{roles}</if> " +
            " where id=#{id}</script> ")
    @ResultMap(value="sys_user")
    void user_updata(Sysuser sysuser);

    @Insert("insert into sys_user(number,password,name,class,role) values(#{username},#{password},#{name},#{BJ},#{roles})")
    @ResultMap(value="sys_user")
    Integer user_add(Sysuser sysuser);

    @Delete("delete from sys_user where id = #{id}")
    @ResultMap(value="sys_user")
    Integer user_delete(int id);
//    @Select("select *" +
//            "from teacher_user " +
//            "where 1=1 and teachernum=#{username} " )
//    @Results({ //2
//            @Result(property = "id", column = "id"), //2
//            @Result(property = "username", column = "teachernum"),
//            @Result(property = "password", column = "password"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "BJ", column = "class"),
//            @Result(property = "roles", column = "role"),
//    })
//    Sysuser teachercx(Sysuser sysuser);

//    @Select("select name from ")
}
