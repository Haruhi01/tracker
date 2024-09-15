package org.haruhi.tracker.database;

import org.haruhi.tracker.goal.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDBManager extends DatabaseManager {
    private static TaskDBManager instance;

    private TaskDBManager(){

    }

    public static TaskDBManager getInstance(){
        if(instance == null){
            instance = new TaskDBManager();
        }
        return instance;
    }

    @Override
    public AbstractTask add(AbstractTask someTask) {
        String sql = "INSERT INTO tasks (epic_id, title, description, status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            if (someTask instanceof Subtask){
                Subtask subtask = (Subtask) someTask;
                pstmt.setInt(1, subtask.getEpicId());
            }
            pstmt.setString(2, someTask.getTitle());
            pstmt.setString(3, someTask.getDescription());
            pstmt.setString(4, someTask.getStatus().name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding task: " + e.getMessage());
        }

        return someTask;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM tasks WHERE id = " + id;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error delete task: " + e.getMessage());
        }
        return false;
    }

    @Override
    public AbstractTask update(AbstractTask someTask) {
        String sql = "UPDATE tasks" +
                "SET title =" + someTask.getTitle() + "," +
                "description =" + someTask.getDescription() + "," +
                "status =" + someTask.getStatus() +
                "WHERE id =" + someTask.getId();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error update task: " + e.getMessage());
        }

        return someTask;
    }

    @Override
    public List<AbstractTask> get() {
        List<AbstractTask> tasks = new ArrayList<>();
        String sql = "SELECT FROM tasks";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                int id = rs.getInt("id");
                Integer epicId = rs.getObject("epic_id", Integer.class);
                String title = rs.getString("title");
                String description = rs.getString("description");
                Status status = Status.valueOf(rs.getString("status"));

                AbstractTask someTask;
                if(epicId != null){
                    someTask = new Subtask(id, title, description, status, epicId);
                } else{
                    someTask = new Task(id, title, description, status);
                }

                tasks.add(someTask);

            }
        } catch (SQLException e) {
            System.err.println("Error update task: " + e.getMessage());
        } catch (IllegalArgumentException e){
            System.err.println("Error update task: " + e.getMessage());
        }

        return tasks;
    }

    @Override
    public AbstractTask find(int id) {
        String sql = "SELECT FROM tasks WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int taskId = rs.getInt("id");
                    Integer epicId = rs.getObject("epic_id", Integer.class);
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Status status = Status.valueOf(rs.getString("status"));

                    if(epicId != null){
                        return new Subtask(taskId, title, description, status, epicId);
                    } else {
                        return new Task(taskId, title, description, status);
                    }
                }

            }
        } catch (SQLException e) {
            System.err.println("Error retrieving task: " + e.getMessage());
        } catch (IllegalArgumentException e){
            System.err.println("Error set status task: " + e.getMessage());
        }

        return null;
    }

    public List<AbstractTask> findBuyEpic(AbstractTask abstractTask) {
        String sql = "SELECT FROM tasks WHERE epic_id = ?";
        List<AbstractTask> subtasks = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, abstractTask.getId());

            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    int taskId = rs.getInt("id");
                    Integer epicId = rs.getObject("epic_id", Integer.class);
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Status status = Status.valueOf(rs.getString("status"));

                    Subtask subtask = new Subtask(taskId, title, description, status, epicId);
                    subtasks.add(subtask);
                }
                return subtasks;
            }

        } catch (SQLException e) {
        System.err.println("Error retrieving task: " + e.getMessage());
        } catch (IllegalArgumentException e){
        System.err.println("Error set status task: " + e.getMessage());
        }
        return null;

    }

}
