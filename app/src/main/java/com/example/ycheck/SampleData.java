package com.example.ycheck;


public class SampleData {
    private String className;
    private String classId;
    private String classStart;
    private String classEnd;
    private String lectureRoom;
    private String professorName;


    // 메인에서 쓰임


    public SampleData(String className, String classId, String classStart, String classEnd, String lectureRoom, String professorName) {
        this.className = className;
        this.classId = classId;
        this.classStart = classStart;
        this.classEnd = classEnd;
        this.lectureRoom = lectureRoom;
        this.professorName = professorName;
    }



    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassStart() {
        return classStart;
    }

    public void setClassStart(String classStart) {
        this.classStart = classStart;
    }

    public String getClassEnd() {
        return classEnd;
    }

    public void setClassEnd(String classEnd) {
        this.classEnd = classEnd;
    }

    public String getLectureRoom() {
        return lectureRoom;
    }

    public void setLectureRoom(String lectureRoom) {
        this.lectureRoom = lectureRoom;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }
}