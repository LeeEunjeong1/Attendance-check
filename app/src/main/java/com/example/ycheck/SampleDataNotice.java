package com.example.ycheck;


public class SampleDataNotice {

    String noticeCategorie;
    String className;
    String noticeTitle;
    String noticeContents;
    String proName;

    public SampleDataNotice(String noticeCategorie, String proName, String classId, String noticeTitle, String noticeContents) {
        this.noticeCategorie = noticeCategorie;
        this.proName=proName;
        this.className = classId;
        this.noticeTitle = noticeTitle;
        this.noticeContents = noticeContents;

    }

    public String getNoticeCategorie() {
        return noticeCategorie;
    }

    public void setNoticeCategorie(String noticeCategorie) {
        this.noticeCategorie = noticeCategorie;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className= className;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContents() {
        return noticeContents;
    }

    public void setNoticeContents(String noticeContents) {
        this.noticeContents = noticeContents;
    }
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
}

