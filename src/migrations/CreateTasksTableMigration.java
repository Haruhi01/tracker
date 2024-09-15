package migrations;

import database.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTasksTableMigration implements Migration{
    private Connection connection = DBConnection.getConnection();

    @Override
    public void up() throws SQLException {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS tasks (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "epic_id INTEGER FOREIGN KEY REFERENCES epics(id)," +
                    "title TEXT NOT NULL," +
                    "description TEXT," +
                    "status TEXT NOT NULL)";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.println("Database initialization error: " + e.getMessage());
        }
    }

    @Override
    public void down() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS tasks");
        }
    }
}
