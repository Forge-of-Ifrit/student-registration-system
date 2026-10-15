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
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Relative path: the database file lives in the project root
    private static final String dbURL = "jdbc:sqlite:StudentReq.db";

    // RETURN A NEW CONNECTION EACH TIME
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbURL);
    }
}