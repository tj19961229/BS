package com.example.demo.entity;

public class Role_name {
    private int role;
    private  String name;
    public String Role_name(int role)
    {
        switch (role){
            case 1:name="学生";break;
            case 2:name="教师";break;
            case 3:name="管理员";break;
        }
        return name;
    }
}
