import ratpack.groovy.template.TextTemplateModule;
import ratpack.guice.Guice;
import ratpack.handling.Chain;
import ratpack.handling.Handler;
import ratpack.http.MutableHeaders;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;
import java.util.Map;

import static ratpack.groovy.Groovy.groovyTemplate;

public class Server {
    public void startServer(Map<String, Handler> getHandlers, Map<String, Handler> postHandlers) throws Exception {
        RatpackServer.start(server -> server
            .serverConfig(config -> config
                    .onError(System.out::println)
                    .baseDir(BaseDir.find())
                    .env())
            .registry(Guice.registry(b -> {
                b.module(TextTemplateModule.class, conf -> conf.setStaticallyCompile(true));
            }))
            .handlers(chain -> {
                chain.prefix("get", api -> {
                    headers(getHandlers, api);
                });
                chain.prefix("post", api -> {
                    headers(postHandlers, api);
                });
                chain.get("", ctx -> ctx.render(groovyTemplate( "index.html")));
                chain.get(":view", ctx -> ctx.render(groovyTemplate( "index.html")));
                chain.files(f -> f.dir("public"));
            })
        );
    }

    private void headers(Map<String, Handler> getHandlers, Chain api) {
        for (Map.Entry<String, Handler> handler: getHandlers.entrySet()) {
            api.path(handler.getKey(), ctx -> {
                MutableHeaders headers = ctx.getResponse().getHeaders();
                headers.set("Access-Control-Allow-Origin", "*");
                headers.set("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
                headers.set("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept, token");
                handler.getValue().handle(ctx);
            });
        }
    }
}