/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentregestrationsystem;

/**
 *
 * @author abdulrahman1
 */
public class Registration {

    private int regId;
    private String studentId;
    private String courseId;
    private String status;   // registered, approved, dropped

    public Registration(int regId, String studentId, String courseId, String status) {
        this.regId = regId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.status = status;
    }

    // Getters & Setters
    public int getRegId() { return regId; }
    public void setRegId(int regId) { this.regId = regId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}