package com.example.demo.service.impl;

import com.example.demo.entity.login;
import com.example.demo.mapper.loginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginService {
    @Autowired
    private loginMapper loginmapper;
    public int _logincx(String username, String userpwd){
        login login_ = new login();
        login_.setUsername(username);
        login_.setUserpwd(userpwd);

        return loginmapper.logincx(login_);
    }
}
