import Models.FoodItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.*;

import java.util.HashMap;
import java.util.List;

public class EventManager {
    static void main(String... args) {

    }

    // Our Event.type should follow a EVENT:TYPE convention
    public static Response dispatchEvent(Event event) throws Exception {
        System.out.println("\n\nEventType \n" + event.getType());
        String domain = event.getType().split(":")[0];

        switch(domain) {
            case "SEARCH":
                return SearchEvent(event);
            case "TEST":
                // Write the return value to a string for transport in the response object.
                return TestEvent(event);
            case "PRIVATE":
                break;
            default:
                System.out.println("Event Not Recognized " + event.getType());
        }
        Response response = new Response(false, "No Event Found", "No Body", event.getType());
        return response;
    }

    /**
     * Test all the data!!!
     * Setup how to handle the event...
     * @param evt
     * @return Response
     * @throws Exception
     */
    private static Response TestEvent(Event evt) throws Exception {
        // Instantiate your manager to consume the Events payload.
        FakeManager fm = new FakeManager();

        String managerDone = fm.doSomething(evt.getPayload());

        // Managers that return a response should use the Response.class
        // if no response from manager still send a Response.ok == true
        Response response = new Response(
                true,
                "Manager done did.",
                managerDone,
                evt.getType()
        );

        return response;
        // TODO Wire up Managers
    }

    private static Response SearchEvent(Event evt) throws Exception {
        ObjectMapper om = new ObjectMapper();

        HashMap result = SearchManager.searchFood(evt.getPayload());
        return  new Response(
                true,
                "Search Results.",
                om.writeValueAsString(result),
                evt.getType()
        );

    }
}
