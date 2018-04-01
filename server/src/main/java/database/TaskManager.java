package database;

import dataformats.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    public static void createTask(Task task, Connection connection) throws SQLException {
        try {
            if (!task.isValid()) {
                throw new SQLException("Invalid task");
            }
            String query = "INSERT INTO task(summary, description, stake, timestamp, deadline, completed, opponentId, tribblerId) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, task.getSummary());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDouble(3, task.getStake());
            preparedStatement.setLong(4, task.getTimestamp());
            preparedStatement.setLong(5, task.getDeadline());
            preparedStatement.setBoolean(6, task.getCompleted());
            preparedStatement.setLong(7, task.getOpponentId());
            preparedStatement.setLong(8, task.getTribblerId());
            preparedStatement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void completeTask(Long taskId, Connection connection) throws SQLException {
        try {
            String query = "UPDATE task SET completed=TRUE WHERE taskId=(?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, taskId);
            preparedStatement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Task getTask(Long taskId, Connection connection) throws SQLException {
        try {
            String query = "SELECT taskId, summary, description, stake, timestamp, deadline, completed, opponentId, tribblerId FROM task WHERE taskId=(?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, taskId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseTask(resultSet);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static List<Task> getAllTasks(Long tribblerId, Connection connection) throws SQLException {
        String query = "SELECT * FROM task WHERE tribblerId=(?);";
        return getTaskList(tribblerId, connection, query);
    }

    public static List<Task> getAllCompletedTasks(Long tribblerId, Connection connection) throws SQLException {
        String query = "SELECT * FROM task WHERE tribblerId=(?) AND completed=TRUE;";
        return getTaskList(tribblerId, connection, query);
    }

    public static List<Task> getAllUncompletedTasks(Long tribblerId, Connection connection) throws SQLException {
        String query = "SELECT * FROM task WHERE tribblerId=(?) AND completed=FALSE;";
        return getTaskList(tribblerId, connection, query);
    }

    private static List<Task> getTaskList(Long tribblerId, Connection connection, String query) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, tribblerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Task> tasks = new ArrayList<>();
            while(resultSet.next()) {
                tasks.add(parseTask(resultSet));
            }
            return tasks;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static Task parseTask(ResultSet resultSet) throws SQLException {
        Long taskId = resultSet.getLong(1);
        String summary = resultSet.getString(2);
        String description = resultSet.getString(3);
        Double stake = resultSet.getDouble(4);
        Long timestamp = resultSet.getLong(5);
        Long deadline = resultSet.getLong(6);
        Boolean completed = resultSet.getBoolean(7);
        Long opponentId = resultSet.getLong(8);
        Long tribblerId = resultSet.getLong(9);

        return new Task(taskId, summary, description, stake, timestamp, deadline, completed, opponentId, tribblerId);
    }

}