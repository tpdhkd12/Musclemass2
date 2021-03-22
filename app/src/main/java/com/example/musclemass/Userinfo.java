package com.example.musclemass;

public class Userinfo {
    private String id;
    private String pw;
    private String name;
    private String nickname;

    public Userinfo(String id, String pw, String name, String nickname) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
