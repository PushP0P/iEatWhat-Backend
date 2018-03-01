package iEatWhatEvents;

import Managers.FakeManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import static Managers.FoodSearchManager.SearchEvent;
import static Managers.UserManager.UserEvent;

public class EventHandler {
    private static ObjectMapper om = new ObjectMapper();
    static void main(String... args) {

    }

    // Our iEatWhatEvents.Event.type should follow a EVENT:TYPE convention
    public static Response dispatchEvent(Event event) throws Exception {
        System.out.println("\n\nEventType \n" + event.getType());
        String domain = event.getType().split(":")[0];

        switch(domain) {
            case "SEARCH":
                return SearchEvent(event);
            case "USER":
                return UserEvent(event);
            case "TEST":
                // Write the return value to a string for transport in the response object.
                return TestEvent(event);
            case "PRIVATE":
                break;
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
