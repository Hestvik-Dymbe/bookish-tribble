package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import database.Database;
import server.handler.TaskHandler;

public class Server {
    private final Thread serverThread;
    private final HttpServer httpServer;
    private final Database database;

    public Server(int port, String databaseName, String username, String password) throws IOException{
        this.database = new Database(databaseName, username, password);

        httpServer = HttpServer.create(new InetSocketAddress(port), 0);

        httpServer.createContext("/test", new TaskHandler(database));

        serverThread = new Thread() {
            @Override
            public void run(){
                System.out.println("Server now listening on port " + port);
                httpServer.start();
            }
        };
    }

    public void run(){
        serverThread.run();
    }
    public void stop(){
        serverThread.interrupt();
    }

    public static void main(String... args) {
        try {
            new Server(46323, "tribble", "postgres", "root").run();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
