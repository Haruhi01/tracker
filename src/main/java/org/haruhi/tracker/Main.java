package org.haruhi.tracker;

import org.haruhi.tracker.database.DBConnection;
import org.haruhi.tracker.database.EpicDBManager;
import org.haruhi.tracker.database.TaskDBManager;
import org.haruhi.tracker.migrations.*;

public class Main {
    public static void main(String[] args) {


        MigrationManager manager = new MigrationManager();

//        manager.addMigration(new CreateEpicsTableMigration());
//        manager.addMigration(new CreateTasksTableMigration());
//
//        manager.migrateUp();




    }
}
