
public class Main {
    public static void main(String... args) throws Exception {
        initializeAPI();
    }

    private static void initializeAPI() throws Exception {
        Server server = new Server();
        API API = new API();
        server.startServer(API.getRequestHandlers(), API.postRequestHandlers());
    }

}