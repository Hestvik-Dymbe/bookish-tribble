package server.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import database.Database;
import dataformats.Task;

import java.io.IOException;
import java.net.URI;

public class TaskHandler extends HttpRequestHandler {

    private Database database;

    public TaskHandler(Database database) {
        this.database = database;
    }

    @Override
    protected void handleGet(HttpExchange httpExchange) throws IOException {
        Gson gson = new Gson();
        URI uri = httpExchange.getRequestURI();
        String body = uri.toString();
        sendResponse(httpExchange, 200, body);
    }

    @Override
    protected void handlePost(HttpExchange httpExchange) throws IOException {
        String body = readBody(httpExchange);
        sendResponse(httpExchange, 200, body + "hallalalal");
    }
}
