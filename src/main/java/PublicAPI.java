import java.util.HashMap;
import java.util.Map;

public class PublicAPI {

    public Map<String, Server.GetHandler> getRequestHandlers() throws Exception {
        Map<String, Server.GetHandler> handlers = new HashMap<>();
        handlers.put("", (ctx) -> ctx.render("FOO BAT METAL"));
        handlers.put("public", (ctx) -> ctx.render("Public API Dynamic Success!"));
        return handlers;
    }
}
