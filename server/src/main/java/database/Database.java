package database;

import dataformats.Task;

import java.sql.*;
import java.util.List;

public class Database {

    String url;
    String username;
    String password;

    public Database(String database, String username, String password) {
        this.url = "jdbc:postgresql:" + database;
        this.username = username;
        this.password = password;
    }

    public void addTask(Task task) throws SQLException {
        TaskManager.createTask(task, getConnection());
    }

    public Task getTask(Long taskId) throws SQLException {
        return TaskManager.getTask(taskId, getConnection());
    }

    public void completeTask(Long taskId) throws SQLException {
        TaskManager.completeTask(taskId, getConnection());
    }

    public List<Task> getAllTasks(Long tribblerId) throws SQLException {
        return TaskManager.getAllTasks(tribblerId, getConnection());
    }

    public List<Task> getAllCompletedTasks(Long tribblerId) throws SQLException {
        return TaskManager.getAllCompletedTasks(tribblerId, getConnection());
    }

    public List<Task> getAllUncompletedTasks(Long tribblerId) throws SQLException {
        return TaskManager.getAllUncompletedTasks(tribblerId, getConnection());
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        try {
            Database db = new Database("tribble", "postgres", "root");
            System.out.println(db.getAllCompletedTasks(null));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
