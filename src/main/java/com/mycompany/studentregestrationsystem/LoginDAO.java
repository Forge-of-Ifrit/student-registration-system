/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentregestrationsystem;


/**
 *
 * @author abdulrahman1
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    // Check if student exists with ID + Password
    public boolean loginStudent(String studentId, String password) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM STUDENT WHERE student_id = ? AND password = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, studentId);
        pstmt.setString(2, password);

        ResultSet rs = pstmt.executeQuery();

        boolean found = rs.next();  // true if student exists

        conn.close();
        return found;
    }

    // Check if advisor exists with ID + Password
    public boolean loginAdvisor(String advisorId, String password) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM ADVISOR WHERE advisor_id = ? AND password = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, advisorId);
        pstmt.setString(2, password);

        ResultSet rs = pstmt.executeQuery();

        boolean found = rs.next();  // true if advisor exists

        conn.close();
        return found;
    }

    // Central login method: returns STUDENT, ADVISOR, or INVALID
    public String login(String id, String password) throws SQLException {

        if (loginStudent(id, password)) {
            return "STUDENT";
        } 
        else if (loginAdvisor(id, password)) {
            return "ADVISOR";
        } 
        else {
            return "INVALID";
        }
    }

    // Load Person information after login
    public Person loadPerson(String personId) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM PERSON WHERE person_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, personId);
        ResultSet rs = pstmt.executeQuery();

        Person person = null;

        if (rs.next()) {
            // Basic person info (type decided by caller)
            person = new Person(
                rs.getString("person_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone_number")
            ) {
                @Override
                public void showInfo() { }

                @Override
                public boolean validate() { return true; }
            };
        }

        conn.close();
        return person;
    }
    public String checkRole(String id, String pass) {
        String sql = "SELECT role FROM Login WHERE id = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, pass);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("role");  // "student" or "advisor"
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // login failed
    }
}