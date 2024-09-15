package org.haruhi.tracker.migrations;


import org.haruhi.tracker.database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MigrationManager{
    private final Connection connection = DBConnection.getConnection();
    private final List<Migration> migrations;

    public MigrationManager() {
        this.migrations = new ArrayList<>();
        createMigrationTableIfNotExist();
    }

    private void createMigrationTableIfNotExist(){
        try (Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS migrations (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "migration_name VARCHAR(255) NOT NULL," +
                    "applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addMigration(Migration migration){
        migrations.add(migration);
    }

    private boolean isMigrationApplied(String migrationName){
        try (PreparedStatement pstmt = connection.prepareStatement(
                "SELECT COUNT(*) FROM migrations WHERE migration_name = ?")) {
            pstmt.setString(1, migrationName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void recordMigration(String migrationName){
        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO migrations (migration_name) VALUES (?)")) {
            pstmt.setString(1, migrationName);
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    private void removeMigrationRecord(String migrationName){
        try (PreparedStatement pstmt = connection.prepareStatement(
                "DELETE FROM migrations WHERE migration_name = ?")) {
            pstmt.setString(1, migrationName);
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void migrateUp(){
        for (Migration migration : migrations) {
            String migrationName = migration.getClass().getSimpleName();
            if (!isMigrationApplied(migrationName)) {
                try {
                    migration.up();
                    recordMigration(migrationName);
                    System.out.println("Applied migration: " + migrationName);
                } catch (SQLException e) {
                    System.err.println("Error applying migration " + migrationName + ": " + e.getMessage());
                }
            }
        }
    }

    public void down(){
        for (int i = migrations.size() - 1; i >= 0; i--) {
            Migration migration = migrations.get(i);
            String migrationName = migration.getClass().getSimpleName();
            if (isMigrationApplied(migrationName)) {
                try {
                    migration.down();
                    removeMigrationRecord(migrationName);
                    System.out.println("Reverted migration: " + migrationName);
                } catch (SQLException e) {
                    System.err.println("Error reverting migration " + migrationName + ": " + e.getMessage());
                }
            }
        }
    }
}
