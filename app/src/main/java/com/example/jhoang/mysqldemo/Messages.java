package com.example.jhoang.mysqldemo;

/**
 * Created by JHoang on 4/10/2016.
 */
public class Messages {
    private String name;
    private int teacherID;
    private String message;

    public Messages(String name, int teacherID, String message){
        this.setName(name);
        this.setTeacherID(teacherID);
        this.setMessage(message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
