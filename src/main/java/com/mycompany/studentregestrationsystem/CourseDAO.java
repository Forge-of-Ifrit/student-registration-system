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

public class CourseDAO {

    // ADD new course
    public void addCourse(Course course) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO COURSE (course_id, course_name, credit_hours) VALUES (?, ?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, course.getCourseId());
        pstmt.setString(2, course.getCourseName());
        pstmt.setInt(3, course.getCreditHours());

        pstmt.executeUpdate();
        conn.close();
    }

    // FIND course by ID
    public Course findCourseById(String courseId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM COURSE WHERE course_id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, courseId);

        ResultSet rs = pstmt.executeQuery();

        Course course = null;

        if (rs.next()) {
            course = new Course(
                rs.getString("course_id"),
                rs.getString("course_name"),
                rs.getInt("credit_hours")
            );
        }

        conn.close();
        return course;
    }

    // GET ALL courses
    public ArrayList<Course> getAllCourses() throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM COURSE";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Course> list = new ArrayList<>();

        while (rs.next()) {
            Course course = new Course(
                rs.getString("course_id"),
                rs.getString("course_name"),
                rs.getInt("credit_hours")
            );

            list.add(course);
        }

        conn.close();
        return list;
    }

    // UPDATE Course
    public void updateCourse(Course course) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "UPDATE COURSE SET course_name = ?, credit_hours = ? WHERE course_id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, course.getCourseName());
        pstmt.setInt(2, course.getCreditHours());
        pstmt.setString(3, course.getCourseId());

        pstmt.executeUpdate();
        conn.close();
    }

    // DELETE Course
    public void deleteCourse(String courseId) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "DELETE FROM COURSE WHERE course_id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, courseId);

        pstmt.executeUpdate();
        conn.close();
    }
    public List<Course> getCisCourses() throws SQLException {
        List<Course> courseList = new ArrayList<>();

        String sql = "SELECT course_id, course_name, credit_hours FROM Course WHERE course_id LIKE 'CIS%'";

        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String id = rs.getString("course_id");
            String name = rs.getString("course_name");
            int credits = rs.getInt("credit_hours");

            courseList.add(new Course(id, name, credits));
        }

        rs.close();
        pstmt.close();
        conn.close();

        return courseList;
    }
}
