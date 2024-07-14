package com.astonhome.firsttomcat.utils;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/ASTON670";
    private static final String USER = "AstonUser";
    private static final String PASSWORD = "WhilJhKyrsn6HrmVNqQ6";
    private static final String DRIVER = "org.postgresql.Driver";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(DRIVER+" JDBC Driver not found.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}