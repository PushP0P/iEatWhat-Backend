import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ratpack.http.MutableHeaders;

import java.util.HashMap;
import java.util.Map;

import static ratpack.jackson.Jackson.json;

public class PublicAPI {

    public static class ArticleContent {
        private String title;
        private String blurb;

        public ArticleContent() {
            this.title = "Testing JSON";
            this.blurb = "Successful JSON Transport and Parse";
        }
    }

    public Map<String, Server.GetHandler> getRequestHandlers() {
        Map<String, Server.GetHandler> handlers = new HashMap<>();
        handlers.put("", (ctx) -> ctx.render("FOO BAT METAL"));
        handlers.put("public", (ctx) -> ctx.render("Public API Dynamic Success!"));
        handlers.put(
            "json",
            (ctx) -> {
                final JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
                ObjectNode articleJSON = jsonNodeFactory.objectNode();
                ArticleContent articleContent = new ArticleContent();
                articleJSON.put("title", articleContent.title);
                articleJSON.put("blurb", articleContent.blurb);
                ctx.render(json(articleJSON));
            });
        return handlers;
    }
}
