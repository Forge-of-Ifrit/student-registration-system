/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentregestrationsystem;

/**
 *
 * @author abdulrahman1
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {

    // ============== SINGLETON PATTERN ==============
    
    // Single instance of RegistrationDAO
    private static RegistrationDAO instance;
    
    // Private constructor prevents external instantiation
    private RegistrationDAO() {
        // Constructor is private - no one can do: new RegistrationDAO()
        System.out.println("RegistrationDAO Singleton instance created!");
    }
    
    // Thread-safe method to get the single instance
    public static synchronized RegistrationDAO getInstance() {
        if (instance == null) {
            instance = new RegistrationDAO();
        }
        return instance;
    }
    
    // ============== END SINGLETON PATTERN ==============

    // ADD a new registration (student requests a course)
    public void addRegistration(String studentId, String courseId) {

        String sql = "INSERT INTO Registration (student_id, course_id, status) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);
            pstmt.setString(2, courseId);
            pstmt.setString(3, "Registered");

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // FIND registration by reg_id
    public Registration findRegistrationById(int regId) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM REGISTRATION WHERE reg_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, regId);

        ResultSet rs = pstmt.executeQuery();
        Registration r = null;

        if (rs.next()) {
            r = new Registration(
                rs.getInt("reg_id"),
                rs.getString("student_id"),
                rs.getString("course_id"),
                rs.getString("status")
            );
        }

        conn.close();
        return r;
    }

    // GET all registrations for a specific student
    public ArrayList<Registration> getRegistrationsByStudent(String studentId) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT R.reg_id, R.student_id, R.course_id, R.status, " +
                     "C.course_name, C.credit_hours " +
                     "FROM REGISTRATION R " +
                     "JOIN COURSE C ON R.course_id = C.course_id " +
                     "WHERE R.student_id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, studentId);

        ResultSet rs = pstmt.executeQuery();
        ArrayList<Registration> list = new ArrayList<>();

        while (rs.next()) {
            Registration r = new Registration(
                rs.getInt("reg_id"),
                rs.getString("student_id"),
                rs.getString("course_id"),
                rs.getString("status")
            );

            // Optional: You can call setters if you want more details
            // r.setCourseName(rs.getString("course_name"));

            list.add(r);
        }

        conn.close();
        return list;
    }

    // GET ALL registrations (Advisor screen)  
    public List<Registration> getAllRegistrations() {
        List<Registration> list = new ArrayList<>();

        String sql = "SELECT * FROM Registration";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Registration r = new Registration(
                    rs.getInt("reg_id"),
                    rs.getString("student_id"),
                    rs.getString("course_id"),
                    rs.getString("status")
                );
                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ADVISOR updates registration status
    public boolean updateStatus(int regId, String status) {
        String sql = "UPDATE Registration SET status = ? WHERE reg_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, regId);

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE a registration
    public void deleteRegistration(int regId) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "DELETE FROM REGISTRATION WHERE reg_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, regId);
        pstmt.executeUpdate();

        conn.close();
    }
}