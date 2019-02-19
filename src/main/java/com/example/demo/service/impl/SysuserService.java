package com.example.demo.service.impl;

import com.example.demo.entity.Sysuser;
import com.example.demo.entity.User;
import com.example.demo.mapper.SysuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysuserService {
    @Autowired
    private SysuserMapper sysuserMapper;

    public Sysuser User_select(String name){
        Sysuser user =new Sysuser();
        user.setUsername(name);
        return  sysuserMapper.user_select(user);
    }
    public  List<Sysuser> Users_select() {

        return  sysuserMapper.users_select();
    }

    public String User_updata(int id,String Columnname,String oldvalue,String newvalue){
        if(oldvalue!=newvalue){
            Sysuser user =new Sysuser();
            switch (Columnname){
                case "name":user.setName(newvalue); System.out.println("namename");break;
                case "bj":user.setBJ(newvalue); System.out.println("bjbj");break;
                case "roles":user.setRoles(Integer.parseInt(newvalue)); System.out.println("rolesroles");
                break;
            }
            user.setId(id);
            sysuserMapper.user_updata(user);
            return "success";
        }
        else
            return "xiangtong";
    }
    public  String User_add(Sysuser sysuser){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        sysuser.setPassword(bCryptPasswordEncoder.encode(sysuser.getPassword()));
        if(sysuserMapper.user_add(sysuser)!=0)
        return "success";
        else return  "error";
    }

    public  String User_delete(String id){
        String[] ss = id.split(",");
        int value_count=0,value_notcount=0;
        for (String s: ss) {
            if(Integer.parseInt(s)!=1){
                sysuserMapper.user_delete(Integer.parseInt(s));
                value_count++;
            }
            else value_notcount++;

        }

        return value_count+","+value_notcount;

    }

//    public int registeruser(String name,String pwd){
//        Sysuser user=new Sysuser();
//        user.setUsername(name);
//        user.setPassword(pwd);
//        return sysuserMapper.registeruser(user);
//    }

}
