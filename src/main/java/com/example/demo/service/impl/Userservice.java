package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Userservice {
    @Autowired
    private UserMapper usermapper;

    public List<User> show(){
        return usermapper.getall();
    }
    public String insert(String username, String userpwd,String nickname,int t_o_s){
        User user = new User();
        user.setUsername(username);
        user.setUserpwd(userpwd);
        user.setNickname(nickname);
        user.setT_o_s(t_o_s);
        usermapper.insert(user);
        return "Insert ( \""+username+"\", age"+nickname+") OK!";
    }
}
