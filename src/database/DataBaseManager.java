package database;

import goal.AbstractTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public abstract class DataBaseManager {

    abstract AbstractTask add(AbstractTask someTask);
    abstract boolean delete(int id);
    abstract AbstractTask update(AbstractTask someTask);
    abstract List<AbstractTask> get();
    abstract AbstractTask find(int id);



}
