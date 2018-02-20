import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.subjects.Subject;

public class EventManager {
    private Subject<String> FakeFoodQuery;

    static void main(String... args) {

    }

    // Our Event.type should follow a DOMAIN:MANAGER-NAME_TYPE convention
    public static String dispatchEvent(Event event) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        System.out.println("\n\nEventType \n" + event.getType());
        String domain = event.getType().split(":")[0];

        switch(domain) {
            case "PRIVATE":
                break;
            default:
                System.out.println("Event Not Recognized " + event.getType());
        }
        Response response = new Response(false, "No Event Found", "No Body", event.getType());
        return om.writeValueAsString(response);
    }

    private static void FoodQuery(String querey) {
        // TODO Wire up Managers
    }
}