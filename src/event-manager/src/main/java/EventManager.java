import Models.FoodItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.*;

import java.util.List;

public class EventManager {
    static void main(String... args) {

    }

    // Our Event.type should follow a DOMAIN:MANAGER-NAME_TYPE convention
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
        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.getTransaction();
//        FoodItem.add(session, transaction,"1234", "foo123142", "Test", "lorem ipsum" );
        List<FoodItem> foodItemList = FoodItem.getAll(session);

//       SearchManager searchManager = new SearchManager();
        return  new Response(
                true,
                "Search Results.",
                om.writeValueAsString(foodItemList),
                evt.getType()
        );

    }
}
