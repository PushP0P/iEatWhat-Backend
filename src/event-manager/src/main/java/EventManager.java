import SearchManager.FoodSearch;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;

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
     * Each event handler should be static
     * Shows: Setup how to handle an event.
     * @param evt
     * @return Response
     * @throws Exception
     */
    private static Response TestEvent(Event evt) throws Exception {
        // Instantiate your manager to consume the Events payload.
        FakeManager fm = new FakeManager();

        String managerDone = fm.doSomething(evt.getPayload());

        // Managers that return a response should use the Response.class
        // if manager is not suppose to respond then manually send a Response.ok == true
        Response response = new Response(
                true,
                "Manager is done.",
                managerDone,
                evt.getType()
        );

        return response;
        // TODO Wire up Managers
    }

    private static Response SearchEvent(Event evt) throws Exception {
        ObjectMapper om = new ObjectMapper();
        String stringResult = FoodSearch.searchFood();
        return  new Response(
                true,
                stringResult,//"Search Results.",
                "Test insert",
                evt.getType()
        );
    }
}
