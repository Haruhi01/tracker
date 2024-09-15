package org.haruhi.tracker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:database.db";
            return DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
