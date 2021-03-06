package com.cuiuc.entity;

public class Blogger {
    private int id;
    private String userName;
    private String passWord;
    private String profile;
    private String nickName;
    private String sign;
    private String imageName;

    public Blogger() {
    }

    public Blogger(String userName, String passWord, String profile, String nickName, String sign, String imageName) {
        this.userName = userName;
        this.passWord = passWord;
        this.profile = profile;
        this.nickName = nickName;
        this.sign = sign;
        this.imageName = imageName;
    }

    public Blogger(int id, String userName, String passWord, String profile, String nickName, String sign, String imageName) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.profile = profile;
        this.nickName = nickName;
        this.sign = sign;
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
