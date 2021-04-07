package com.example.musclemass;


import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class Communityitem {


    private String titleStr ;
    private String descStr ;
    private String idStr ;
    private String day;
    // 아이템 id //
    private String timestamp;

    private String nickname;

    public Communityitem(String titleStr, String descStr, String idStr, String day, String timestamp, String nickname) {
        this.titleStr = titleStr;
        this.descStr = descStr;
        this.idStr = idStr;
        this.day = day;
        this.timestamp = timestamp;
        this.nickname = nickname;
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

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

