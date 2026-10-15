/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentregestrationsystem;

/**
 *
 * @author abdulrahman1
 */
public class Advisor extends Person {

    private String advisorId;
    private String password;

    public Advisor(String advisorId, String name, String email, String phoneNumber, 
                   String password) {

        super(advisorId, name, email, phoneNumber);
        this.advisorId = advisorId;
        this.password = password;
    }

    // Getters & Setters
    public String getAdvisorId() { return advisorId; }
    public void setAdvisorId(String advisorId) { this.advisorId = advisorId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public void showInfo() {
        System.out.println("Advisor Info:");
        System.out.println("ID: " + advisorId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }

    @Override
    public boolean validate() {
        return advisorId != null && !advisorId.isEmpty() &&
               name != null && !name.isEmpty();
    }
}
