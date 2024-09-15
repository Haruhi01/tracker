package migrations;

import java.sql.Connection;
import java.sql.SQLException;

public interface Migration {
    void up() throws SQLException;
    void down() throws SQLException;
}
