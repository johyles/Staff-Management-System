package com.gy.entity;

import java.util.Date;

public class Emp {
    private Integer id;//员工编号
    private  String username ;//名字
    private  Integer  age;//年龄
    private  String info;//简介
    private String hobby ;//爱好
    private  String sex;//性别
    private Date hireDate ;//入职时间
    private  String imgUrl;//头像
    private Dept dept;//部门编号

    public Emp() {
    }

    public Emp(Integer id, String username, Integer age, String info, String hobby, String sex, Date hireDate, String imgUrl, Dept dept) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.info = info;
        this.hobby = hobby;
        this.sex = sex;
        this.hireDate = hireDate;
        this.imgUrl = imgUrl;
        this.dept=dept;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
