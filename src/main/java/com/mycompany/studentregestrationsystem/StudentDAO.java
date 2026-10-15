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

public class StudentDAO {

    // INSERT new student (PERSON + STUDENT)
    public void addStudent(Student student) throws SQLException {
        Connection conn = DBConnection.getConnection();

        // Insert into PERSON table first
        String sqlPerson = "INSERT INTO PERSON(person_id, name, email, phone_number) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt1 = conn.prepareStatement(sqlPerson);
        pstmt1.setString(1, student.getStudentId());
        pstmt1.setString(2, student.getName());
        pstmt1.setString(3, student.getEmail());
        pstmt1.setString(4, student.getPhoneNumber());
        pstmt1.executeUpdate();

        // Insert into STUDENT table
        String sqlStudent = "INSERT INTO STUDENT(student_id, major, password) VALUES (?, ?, ?)";
        PreparedStatement pstmt2 = conn.prepareStatement(sqlStudent);
        pstmt2.setString(1, student.getStudentId());
        pstmt2.setString(2, student.getMajor());
        pstmt2.setString(3, student.getPassword());
        pstmt2.executeUpdate();

        conn.close();
    }

    // FIND student by ID
    public Student findStudentById(String studentId) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT P.person_id, P.name, P.email, P.phone_number, " +
                     "S.major, S.password " +
                     "FROM PERSON P JOIN STUDENT S ON P.person_id = S.student_id " +
                     "WHERE S.student_id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, studentId);

        ResultSet rs = pstmt.executeQuery();

        Student student = null;
        if (rs.next()) {
            student = new Student(
                rs.getString("person_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getString("major"),
                rs.getString("password")
            );
        }

        conn.close();
        return student;
    }

    // GET ALL STUDENTS
    public List<Student> getAllStudents() throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT P.person_id, P.name, P.email, P.phone_number, " +
                     "S.major, S.password " +
                     "FROM PERSON P JOIN STUDENT S ON P.person_id = S.student_id";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<Student> students = new ArrayList<>();

        while (rs.next()) {
            Student student = new Student(
                rs.getString("person_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getString("major"),
                rs.getString("password")
            );
            students.add(student);
        }

        conn.close();
        return students;
    }

    // UPDATE student (both PERSON & STUDENT)
    public void updateStudent(Student student) throws SQLException {
        Connection conn = DBConnection.getConnection();

        // Update PERSON table
        String sqlPerson = "UPDATE PERSON SET name = ?, email = ?, phone_number = ? WHERE person_id = ?";
        PreparedStatement pstmt1 = conn.prepareStatement(sqlPerson);
        pstmt1.setString(1, student.getName());
        pstmt1.setString(2, student.getEmail());
        pstmt1.setString(3, student.getPhoneNumber());
        pstmt1.setString(4, student.getStudentId());
        pstmt1.executeUpdate();

        // Update STUDENT table
        String sqlStudent = "UPDATE STUDENT SET major = ?, password = ? WHERE student_id = ?";
        PreparedStatement pstmt2 = conn.prepareStatement(sqlStudent);
        pstmt2.setString(1, student.getMajor());
        pstmt2.setString(2, student.getPassword());
        pstmt2.setString(3, student.getStudentId());
        pstmt2.executeUpdate();

        conn.close();
    }

    // DELETE student (delete STUDENT first, then PERSON)
    public void deleteStudent(String studentId) throws SQLException {
        Connection conn = DBConnection.getConnection();

        // Delete from STUDENT
        String sqlStudent = "DELETE FROM STUDENT WHERE student_id = ?";
        PreparedStatement pstmt1 = conn.prepareStatement(sqlStudent);
        pstmt1.setString(1, studentId);
        pstmt1.executeUpdate();

        // Delete from PERSON
        String sqlPerson = "DELETE FROM PERSON WHERE person_id = ?";
        PreparedStatement pstmt2 = conn.prepareStatement(sqlPerson);
        pstmt2.setString(1, studentId);
        pstmt2.executeUpdate();

        conn.close();
    }
}