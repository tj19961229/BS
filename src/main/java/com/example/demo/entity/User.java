package com.example.demo.entity;

public class User {
    private int id;



    private String username;
    private String userpwd;
    private String nickname;
    private int t_o_s;

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

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getT_o_s() {
        return t_o_s;
    }

    public void setT_o_s(int t_o_s) {
        this.t_o_s = t_o_s;
    }
}
