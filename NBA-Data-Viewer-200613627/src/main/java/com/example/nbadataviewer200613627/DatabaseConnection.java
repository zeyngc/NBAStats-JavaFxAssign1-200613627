package com.example.nbadataviewer200613627;

import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) {
        try {
            DatabaseConnection connection = (DatabaseConnection) DriverManager.getConnection("jdbc:mysql://172.31.22.43:3306/Zeyn200613627", "Zeyn200613627", "ucjlb4yMwA");
        } catch (Exception e) {
            System.err.println("Error found: " + e.getMessage());
        }
    }
}
