import database.DBConnection;
import database.EpicDBManager;
import database.TaskDBManager;
import migrations.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {


        MigrationManager manager = new MigrationManager();

        manager.addMigration(new CreateEpicsTableMigration());
        manager.addMigration(new CreateTasksTableMigration());

        manager.migrateUp();




    }
}
