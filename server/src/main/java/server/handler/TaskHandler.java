package server.handler;

import com.sun.net.httpserver.HttpExchange;
import database.Database;
import dataformats.Task;
import server.handler.util.QueryMap;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TaskHandler extends HttpRequestHandler {

    private Database database;

    public TaskHandler(Database database) {
        this.database = database;
    }

    @Override
    protected void handleGet(HttpExchange httpExchange) throws IOException {
        System.out.println("1");
        String query = httpExchange.getRequestURI().getQuery();
        if (query == null) {
            sendResponse(httpExchange, 400, "Empty query");
        } else {
            System.out.println("2");
            QueryMap queryMap = new QueryMap(query);
            if (queryMap.containsKey("taskId")) {
                System.out.println("3");
                sendSingleTask(queryMap.getLong("taskId"), httpExchange);
            } else if (queryMap.containsKey("tribblerId")) {
                if (queryMap.containsKey("completed")) {
                    if (queryMap.isTrue("completed")) {
                        sendCompletedTasks(queryMap.getLong("tribblerId"), httpExchange);
                    } else if (queryMap.isFalse("completed")) {
                        sendUncompletedTasks(queryMap.getLong("tribblerId"), httpExchange);
                    }
                } else {
                    sendAllTasks(queryMap.getLong("tribblerId"), httpExchange);
                }
            }
        }
    }

    private void sendSingleTask(Long taskId, HttpExchange httpExchange) throws IOException {
        try {
            Task task = database.getTask(taskId);
            String json = gson.toJson(task);
            sendResponse(httpExchange, 200, json);
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(httpExchange, 404, "Could not find specified task");
        }
    }

    private void sendCompletedTasks(Long tribblerId, HttpExchange httpExchange) throws IOException {
        try {
            List<Task> tasks = database.getAllCompletedTasks(tribblerId);
            String json = gson.toJson(tasks);
            sendResponse(httpExchange, 200, json);
        } catch (SQLException e) {
            sendResponse(httpExchange, 404, "Could not find specified tribbler");
        }
    }

    private void sendUncompletedTasks(Long tribblerId, HttpExchange httpExchange) throws IOException {
        try {
            List<Task> tasks = database.getAllUncompletedTasks(tribblerId);
            String json = gson.toJson(tasks);
            sendResponse(httpExchange, 200, json);
        } catch (SQLException e) {
            sendResponse(httpExchange, 404, "Could not find specified tribbler");
        }
    }

    private void sendAllTasks(Long tribblerId, HttpExchange httpExchange) throws IOException {
        try {
            List<Task> tasks = database.getAllTasks(tribblerId);
            String json = gson.toJson(tasks);
            sendResponse(httpExchange, 200, json);
        } catch (SQLException e) {
            sendResponse(httpExchange, 404, "Could not find specified tribbler");
        }
    }

    @Override
    protected void handlePost(HttpExchange httpExchange) throws IOException {
        String body = readBody(httpExchange);
        System.out.println("why");
        sendResponse(httpExchange, 200, body + "hadsallalalal");
    }

}
