import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iEatWhat.API;
import iEatWhat.Server;
import iEatWhatEvents.Event;
import iEatWhatEvents.EventHandler;
import iEatWhatEvents.Response;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String... args) {
        ObjectMapper om = new ObjectMapper();
        try {
            initializeAPI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Response result = null;
        try {
            result = EventHandler.dispatchInternalEvent(new Event("UPDATE_COLLECTIONS", ""));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(om.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static void initializeAPI() {
        Server server = new Server();
        API API = new API();
        server.startServer(API.getRequestHandlers(), API.postRequestHandlers());
    }
}