package org.haruhi.tracker.migrations;

import org.haruhi.tracker.database.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class MigrationSchema implements Migration {
    protected final Connection connection = DBConnection.getConnection();

    @Override
    public abstract void up() throws SQLException;

    @Override
    public abstract void down() throws SQLException;
}
