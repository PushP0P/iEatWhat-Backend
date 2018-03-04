package iEatWhat;

import iEatWhatEvents.Event;
import iEatWhatEvents.EventHandler;

public class Main {
    public static void main(String... args) {
        try {
            initializeAPI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        EventHandler.dispatchInternalEvent(new Event("UPDATE_COLLECTIONS", ""));
    }

    private static void initializeAPI() throws Exception {
        Server server = new Server();
        API API = new API();
        server.startServer(API.getRequestHandlers(), API.postRequestHandlers());
    }

}