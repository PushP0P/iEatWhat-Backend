import com.fasterxml.jackson.databind.ObjectMapper;
import iEatWhatEvents.Event;
import iEatWhatEvents.EventHandler;
import ratpack.exec.Promise;
import ratpack.form.Form;
import ratpack.handling.Handler;

import java.util.HashMap;
import java.util.Map;

public class API {
    private ObjectMapper om = new ObjectMapper();

    public Map<String, Handler> getRequestHandlers() {
        Map<String, Handler> handlers = new HashMap<>();

        // Get Endpoints and handler are put in to the Map here...

        return handlers;
    }

    public Map<String, Handler> postRequestHandlers() {
        Map<String, Handler> handlers = new HashMap<>();
        handlers.put("event", (ctx) -> {
            Promise<Form> form = ctx.parse(Form.class);
            form.then(f -> {

                // System.out.println("Form Data? \n\n" + f.getAll());

                // if the form data is not an iEatWhatEvents.Event Object
                if (f.get("type") == null){
                    ctx.render("{\"message\": \"No Body\"}");
                } else {
                    Event evt = new Event(f.get("type"), f.get("payload"));
                    String response = this.om.writeValueAsString(EventHandler.dispatchEvent(evt));
                    ctx.render(response);
                }
            });
        });
        return handlers;
    }
}
