import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.subjects.Subject;

public class EventManager {
    private Subject<String> FakeFoodQuery;

    static void main(String... args) {

    }

    public static String dispatchEvent(Event event) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        String domain = event.getType().split(":")[0];

        switch (domain) {
            case "PUBLIC":
                return om.writeValueAsString(PublicEvents(event));
            case "PRIVATE":
                break;
            default:
                System.out.println("Event Not Recognized " + event.getType());
        }
        Response response = new Response(false, "No Event Found", "No Body", event);
        return om.writeValueAsString(response);
    }

    private static Response PublicEvents(Event event) {
        return new Response(true, "OK", "{foo:'bar', test:'Success!'}", event);
    }

    private static void FoodQuerey(String querey) {

    }


}