package com.mycompany.studentregestrationsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abdulrahman1
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdvisorDAO {

    // ADD NEW ADVISOR (Insert into PERSON then ADVISOR)
    public void addAdvisor(Advisor advisor) throws SQLException {
        Connection conn = DBConnection.getConnection();

        // Insert into PERSON table
        String sqlPerson = "INSERT INTO PERSON(person_id, name, email, phone_number) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt1 = conn.prepareStatement(sqlPerson);
        pstmt1.setString(1, advisor.getAdvisorId());
        pstmt1.setString(2, advisor.getName());
        pstmt1.setString(3, advisor.getEmail());
        pstmt1.setString(4, advisor.getPhoneNumber());
        pstmt1.executeUpdate();

        // Insert into ADVISOR table
        String sqlAdvisor = "INSERT INTO ADVISOR(advisor_id, password) VALUES (?, ?)";
        PreparedStatement pstmt2 = conn.prepareStatement(sqlAdvisor);
        pstmt2.setString(1, advisor.getAdvisorId());
        pstmt2.setString(2, advisor.getPassword());
        pstmt2.executeUpdate();

        conn.close();
    }

    // FIND ADVISOR BY ID (Using JOIN)
    public Advisor findAdvisorById(String advisorId) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT P.person_id, P.name, P.email, P.phone_number, " +
                     "A.password " +
                     "FROM PERSON P JOIN ADVISOR A ON P.person_id = A.advisor_id " +
                     "WHERE A.advisor_id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, advisorId);

        ResultSet rs = pstmt.executeQuery();

        Advisor advisor = null;

        if (rs.next()) {
            advisor = new Advisor(
                rs.getString("person_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getString("password")
            );
        }

        conn.close();
        return advisor;
    }

    // GET ALL ADVISORS
    public List<Advisor> getAllAdvisors() throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT P.person_id, P.name, P.email, P.phone_number, " +
                     "A.password " +
                     "FROM PERSON P JOIN ADVISOR A ON P.person_id = A.advisor_id";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<Advisor> advisors = new ArrayList<>();

        while (rs.next()) {
            Advisor advisor = new Advisor(
                rs.getString("person_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getString("password")
            );

            advisors.add(advisor);
        }

        conn.close();
        return advisors;
    }

    // UPDATE ADVISOR INFORMATION
    public void updateAdvisor(Advisor advisor) throws SQLException {
        Connection conn = DBConnection.getConnection();

        // Update PERSON info
        String sqlPerson = "UPDATE PERSON SET name = ?, email = ?, phone_number = ? WHERE person_id = ?";
        PreparedStatement pstmt1 = conn.prepareStatement(sqlPerson);
        pstmt1.setString(1, advisor.getName());
        pstmt1.setString(2, advisor.getEmail());
        pstmt1.setString(3, advisor.getPhoneNumber());
        pstmt1.setString(4, advisor.getAdvisorId());
        pstmt1.executeUpdate();

        // Update ADVISOR info
        String sqlAdvisor = "UPDATE ADVISOR SET password = ? WHERE advisor_id = ?";
        PreparedStatement pstmt2 = conn.prepareStatement(sqlAdvisor);
        pstmt2.setString(1, advisor.getPassword());
        pstmt2.setString(2, advisor.getAdvisorId());
        pstmt2.executeUpdate();

        conn.close();
    }

    // DELETE ADVISOR
    public void deleteAdvisor(String advisorId) throws SQLException {
        Connection conn = DBConnection.getConnection();

        // FIRST delete from ADVISOR (child table)
        String sqlAdvisor = "DELETE FROM ADVISOR WHERE advisor_id = ?";
        PreparedStatement pstmt1 = conn.prepareStatement(sqlAdvisor);
        pstmt1.setString(1, advisorId);
        pstmt1.executeUpdate();

        // THEN delete from PERSON (parent table)
        String sqlPerson = "DELETE FROM PERSON WHERE person_id = ?";
        PreparedStatement pstmt2 = conn.prepareStatement(sqlPerson);
        pstmt2.setString(1, advisorId);
        pstmt2.executeUpdate();

        conn.close();
    }
}
