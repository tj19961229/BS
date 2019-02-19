package com.example.demo.entity;
import javafx.scene.input.DataFormat;

import  java.util.Date;
public class User_miniui {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBumen() {
        return bumen;
    }

    public void setBumen(String bumen) {
        this.bumen = bumen;
    }

    private String loginname;
    private String name;
    private int gender;
    private int age;
    private String birthday;
    private String bumen;
}
