package com.example.demo.entity;

import java.util.List;

public class Sysuser {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBJ() {
        return BJ;
    }

    public void setBJ(String BJ) {
        this.BJ = BJ;
    }

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }

    private int id;
    private String username;
    private  String password;
    private  String name;
    private  String BJ;
    private int roles;

}
