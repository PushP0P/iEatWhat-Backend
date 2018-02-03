public class Main {
    public static void main(String... args) throws Exception {
        initializeAPI();
    }

    private static void initializeAPI() throws Exception {
        Server server = new Server();
        PublicAPI publicAPI = new PublicAPI();
        server.startServer(publicAPI.getRequestHandlers());
    }
}