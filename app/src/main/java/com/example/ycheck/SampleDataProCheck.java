package com.example.ycheck;

public class SampleDataProCheck {
    private String className;
    private String stuName;
    private String checkWhether;
    private String checkTime;

    public SampleDataProCheck(String className, String stuName, String checkWhether, String checkTime) {
        this.className = className;
        this.stuName = stuName;
        this.checkWhether = checkWhether;
        this.checkTime = checkTime;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setCheckWhether(String checkWhether) {
        this.checkWhether = checkWhether;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }




    // 메인에서 쓰임


    public String getClassName() {
        return className;
    }

    public String getStuName() {
        return stuName;
    }

    public String getCheckWhether() {
        return checkWhether;
    }

    public String getCheckTime() {
        return checkTime;
    }



}
