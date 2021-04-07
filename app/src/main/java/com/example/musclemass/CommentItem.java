package com.example.musclemass;

import android.net.Uri;

public class CommentItem {

    private String itemid;
    private String date;
    private String userid;
    private String context;
    private String nickname;
    private String uri;
    private String postid;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public CommentItem(String itemid, String date, String userid, String context, String nickname, String uri,String postid) {
        this.itemid = itemid;
        this.date = date;
        this.userid = userid;
        this.context = context;
        this.nickname = nickname;
        this.uri = uri;
        this.postid = postid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
