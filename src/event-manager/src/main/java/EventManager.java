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
            case "AUTH":
                return om.writeValueAsString(Auth(event));
            case "PRIVATE":
                break;
            default:
                System.out.println("Event Not Recognized " + event.getType());
        }
        Response response = new Response(false, "No Event Found", "No Body", event.getType());
        return om.writeValueAsString(response);
    }

    private static void FoodQuerey(String querey) {
        // TODO Wire up Managers
    }

    private static Response Auth(Event event) {
        String nameType = event.getType().split(":")[1];
        GAuthManager gAuthManager = new GAuthManager();
        switch (nameType) {
            case"GOOGLE_URL":
                return new Response(true, "Google auth url.", gAuthManager.getGeneratedAuthURL$(), event.getType());
            default:
                return new Response(false, "Auth manager not found.", "No Body", event.getType());
        }
    }

}