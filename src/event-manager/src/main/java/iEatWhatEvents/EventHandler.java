package iEatWhatEvents;

import Managers.FakeManager;
import Workers.FoodData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static Managers.FoodSearchManager.SearchEvent;
import static Managers.UserManager.UserEvent;

public class EventHandler {
    private static ObjectMapper om = new ObjectMapper();
    static void main(String... args) {

    }

    public static Response dispatchInternalEvent(Event event) {
        System.out.println("\n\nEventType \n" + event.getType());
        String domain = event.getType().split(":")[0];
        switch (domain) {
            case "UPDATE_COLLECTIONS":
                try {
                    FoodData.getNutrientListUSDA();
                } catch (ParserConfigurationException | SAXException | IOException e) {
                    e.printStackTrace();
                    return Response.ErrorBuilder.build("Error with event", event.getType());
                }
            default:
                return Response.ErrorBuilder.build("Could not match event of type ", event.getType());
        }

    }

    // Our iEatWhatEvents.Event.type should follow a EVENT:TYPE convention
    public static Response dispatchRESTEvent(Event event) throws Exception {
        System.out.println("\n\nEventType \n" + event.getType());
        String domain = event.getType().split(":")[0];

        switch(domain) {
                // A demonstration of the event/manager/worker pattern used.
            case "FAKE":
                // Write the return value to a string for transport in the response object.

            case "SEARCH":
                return SearchEvent(event);
            case "USER":
                return UserEvent(event);
            default:
                System.out.println("iEatWhatEvents.Event Not Recognized " + event.getType());
        }
        Response response = new Response(false, "No iEatWhatEvents.Event Found", "No Body", event.getType());
        return response;
    }

    /**
     * Each event handler should be static
     * Shows: Setup how to handle an event.
     * @param evt
     * @return iEatWhatEvents.Response
     * @throws Exception
     */
    private static Response TestEvent(Event evt) throws Exception {
        // Instantiate your manager to consume the Events payload.
        FakeManager fm = new FakeManager();

        String managerDone = fm.doSomething(evt.getPayload());

        // Managers that return a response should use the iEatWhatEvents.Response.class
        // if manager is not suppose to respond then manually send a iEatWhatEvents.Response.ok == true
        Response response = new Response(
                true,
                "Manager is done.",
                managerDone,
                evt.getType()
        );

        return response;
        // TODO Wire up Managers
    }

}
