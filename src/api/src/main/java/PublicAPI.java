import com.fasterxml.jackson.databind.ObjectMapper;
import ratpack.handling.Handler;
//import ratpack.handling.Context;

import java.util.HashMap;
import java.util.Map;

public class PublicAPI {

//    public Map<String, Handler> postRequestHandlers() {
//        Map<String, Handler> handlers = new HashMap<>();
//
//        return handlers;
//    }

    public Map<String, Handler> getRequestHandlers() {
        Map<String, Handler> handlers = new HashMap<>();
        handlers.put("", (ctx) -> ctx.render("FOO BAT METAL"));
        handlers.put("public", (ctx) -> ctx.render("Public API Dynamic Success!"));
        handlers.put(
            "demo",
            (ctx) -> {
                ObjectMapper om = new ObjectMapper();
                Article article = Article.demoData();
                String articleAsString = om.writeValueAsString(article);
                ctx.render(articleAsString);
            });
        return handlers;
    }

//    private void eventHandler(Context ctx) {
//
//    }


}

