import com.fasterxml.jackson.databind.ObjectMapper;
import ratpack.exec.Promise;
import ratpack.form.Form;
import ratpack.handling.Handler;

import java.util.HashMap;
import java.util.Map;

public class PublicAPI {
    private ObjectMapper om = new ObjectMapper();

    public Map<String, Handler> getRequestHandlers() {
        Map<String, Handler> handlers = new HashMap<>();

        handlers.put("", (ctx) -> ctx.render("FOO BAT METAL"));
        handlers.put("public", (ctx) -> ctx.render("Public API Dynamic Success!"));
        handlers.put(
            "demo",
            (ctx) -> {
                Article article = Article.demoData();
                String articleAsString = this.om.writeValueAsString(article);
                ctx.render(articleAsString);
            });
        return handlers;
    }

    public Map<String, Handler> postRequestHandlers() {
        Map<String, Handler> handlers = new HashMap<>();
        handlers.put("event", (ctx) -> {
            Promise<Form> form = ctx.parse(Form.class);
            form.then(f -> {
                Event evt = new Event(f.get("type"), f.get("payload"));
                String response = EventManager.dispatchEvent(evt);
                ctx.render(response);
            });
        });
        return handlers;
    }
}