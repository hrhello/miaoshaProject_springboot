package com.miaoshaProject.controller.viewObject;

public class UserVO {
    private Integer id;

    private String name;

    private Byte gendar;

    private Integer age;

    private String telphone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getGendar() {
        return gendar;
    }

    public void setGendar(Byte gendar) {
        this.gendar = gendar;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
}
