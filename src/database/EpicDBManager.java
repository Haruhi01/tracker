package database;

import goal.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EpicDBManager extends DataBaseManager{
    private static EpicDBManager instance;
    private static Connection connection = DBConnection.getConnection();

    private EpicDBManager() {
    }

    public static EpicDBManager getInstance(){
        if(instance == null){
            instance = new EpicDBManager();
        }
        return instance;
    }

    @Override
    public AbstractTask add(AbstractTask someTask) {
        String sql = "INSERT INTO epics (title, description, status) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(2, someTask.getTitle());
            pstmt.setString(3, someTask.getDescription());
            pstmt.setString(4, someTask.getStatus().name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding epic: " + e.getMessage());
        }

        return someTask;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM epics WHERE id = " + id;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error delete epic: " + e.getMessage());
        }
        return false;
    }

    @Override
    public AbstractTask update(AbstractTask someTask) {
        String sql = "UPDATE epics" +
                "SET title =" + someTask.getTitle() + "," +
                "description =" + someTask.getDescription() + "," +
                "status =" + someTask.getStatus() +
                "WHERE id =" + someTask.getId();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error update epic: " + e.getMessage());
        }

        return someTask;
    }

    @Override
    public List<AbstractTask> get() {
        List<AbstractTask> epics = new ArrayList<>();
        String sql = "SELECT FROM epics";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Status status = Status.valueOf(rs.getString("status"));

                AbstractTask someTask = new Epic(id, title, description, status);

                epics.add(someTask);

            }
        } catch (SQLException e) {
            System.err.println("Error retrieving epic: " + e.getMessage());
        } catch (IllegalArgumentException e){
            System.err.println("Error set status epic: " + e.getMessage());
        }
        return epics;
    }

    @Override
    public AbstractTask find(int id) {
        String sql = "SELECT FROM tasks WHERE id =" + id;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int taskId = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Status status = Status.valueOf(rs.getString("status"));

                    AbstractTask epic = new Epic(taskId, title, description, status);
                    return epic;

                }

            }
        } catch (SQLException e) {
            System.err.println("Error retrieving epic: " + e.getMessage());
        } catch (IllegalArgumentException e){
            System.err.println("Error set status epic: " + e.getMessage());
        }
        return null;
    }

}
