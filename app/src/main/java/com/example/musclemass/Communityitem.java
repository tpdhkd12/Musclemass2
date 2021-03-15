package com.example.musclemass;


import android.graphics.drawable.Drawable;

public class Communityitem {


    private String titleStr ;
    private String descStr ;
    private String nicknameStr ;

    public Communityitem(String titleStr, String descStr, String nicknameStr) {
        this.titleStr = titleStr;
        this.descStr = descStr;
        this.nicknameStr = nicknameStr;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public String getDescStr() {
        return descStr;
    }

    public void setDescStr(String descStr) {
        this.descStr = descStr;
    }

    public String getNicknameStr() {
        return nicknameStr;
    }

    public void setNicknameStr(String nicknameStr) {
        this.nicknameStr = nicknameStr;
    }
}

