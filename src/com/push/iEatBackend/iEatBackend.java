package com.push.iEatBackend;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class iEatBackend {

    public static void main(String[] args) throws IOException {
        iEatBackend instance = new iEatBackend();
        instance.initializeServers();

        //Todo Init DBCLient
    }

    static class RequestHandler implements HttpHandler {

       public void handle(HttpExchange t) throws IOException {
            byte [] response = "FOO BAT METAL \\,,/ ".getBytes();
            t.sendResponseHeaders(200, response.length);
            OutputStream os = t.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    private void initializeServers() throws IOException {
        int pubPort = 1729;
        int privPort = 7337;

        HttpServer pubServer = getServer(pubPort);
        HttpServer privServer = getServer(privPort);
        launchServer("/test-pub", pubServer);
        launchServer("/test-priv", privServer);
    }


    private void launchServer(String url, HttpServer server) {
        server.createContext(url, new RequestHandler());
        server.start();
    }

    private HttpServer getServer(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(null);
        return server;
    }

}