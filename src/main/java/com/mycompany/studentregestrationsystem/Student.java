/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentregestrationsystem;

/**
 *
 * @author abdulrahman1
 */
public class Student extends Person {

    private String studentId;
    private String major;
    private String password;

    public Student(String studentId, String name, String email, String phoneNumber, 
                   String major, String password) {

        super(studentId, name, email, phoneNumber);  
        this.studentId = studentId;
        this.major = major;
        this.password = password;
    }

    // Getters & Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public void showInfo() {
        System.out.println("Student Info:");
        System.out.println("ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Major: " + major);
        System.out.println("Email: " + email);
    }

    @Override
    public boolean validate() {
        return studentId != null && !studentId.isEmpty() &&
               name != null && !name.isEmpty();
    }
}
