package com.example.demo.service.impl;

import com.example.demo.entity.User_miniui;
import com.example.demo.mapper.MiniuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MiniuserService {
    @Autowired
    private MiniuserMapper miniuserMapper;
    public List<User_miniui> mini_select_bumen(String value){
        return miniuserMapper.mini_select_bumen(value);
    }
}
