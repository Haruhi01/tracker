package org.haruhi.tracker.migrations;

import org.haruhi.tracker.database.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateEpicsTableMigration extends MigrationSchema{
    @Override
    public void up() throws SQLException {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS epics (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT NOT NULL," +
                    "description TEXT," +
                    "status TEXT NOT NULL)";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.println("Epics table initialization error: " + e.getMessage());
        }
    }

    @Override
    public void down() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS epics");
        }
    }
}
