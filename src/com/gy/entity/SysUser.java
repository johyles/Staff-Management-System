package com.gy.entity;

public class SysUser {
    private Integer id;
    private String name;
    private String pwd;
    private String email;
    private int status;
    private String username;
    private String phone;
    private String qq;
    private String weibo;
    private String intro;
    private String url;

    public SysUser(Integer id, String name, String pwd, String email, int status, String username, String phone, String qq, String weibo, String intro, String url) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.status = status;
        this.username = username;
        this.phone = phone;
        this.qq = qq;
        this.weibo = weibo;
        this.intro = intro;
        this.url = url;
    }

    public SysUser() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getQq() {
        return qq;
    }

    public String getWeibo() {
        return weibo;
    }

    public String getIntro() {
        return intro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public int getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
