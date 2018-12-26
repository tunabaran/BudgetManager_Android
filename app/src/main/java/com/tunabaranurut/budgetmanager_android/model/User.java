package com.tunabaranurut.budgetmanager_android.model;

public class User {

    private String id;
    private String password;
    private BasicInfo basicInfo;

    public String getId() { return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

}
