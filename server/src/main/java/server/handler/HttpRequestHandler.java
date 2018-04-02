package server.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public abstract class HttpRequestHandler implements HttpHandler {

    Gson gson = new Gson();

    @Override
    public final void handle(HttpExchange httpExchange) throws IOException {
        switch (httpExchange.getRequestMethod()) {
            case "GET":     handleGet(httpExchange);
            case "POST":    handlePost(httpExchange);
            default:        sendResponse(httpExchange, 200, "jalla");
        }
    }

    protected abstract void handleGet(HttpExchange httpExchange) throws IOException;
    protected abstract void handlePost(HttpExchange httpExchange) throws IOException;

    protected static String readBody(HttpExchange httpExchange) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = httpExchange.getRequestBody().read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }

    protected static void sendResponse(HttpExchange httpExchange, int statusCode, String body) throws IOException {
        byte[] bodyBytes = body.getBytes();
        httpExchange.sendResponseHeaders(statusCode, bodyBytes.length);
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(bodyBytes);
        outputStream.close();
    }

}
