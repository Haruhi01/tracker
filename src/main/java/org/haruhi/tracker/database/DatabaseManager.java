package org.haruhi.tracker.database;

import org.haruhi.tracker.goal.AbstractTask;

import java.sql.Connection;
import java.util.List;

public abstract class DatabaseManager {
    protected final Connection connection = DBConnection.getConnection();

    abstract AbstractTask add(AbstractTask someTask);
    abstract boolean delete(int id);
    abstract AbstractTask update(AbstractTask someTask);
    abstract List<AbstractTask> get();
    abstract AbstractTask find(int id);



}
