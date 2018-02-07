<<<<<<< HEAD
=======
import ratpack.groovy.template.TextTemplateModule;
import ratpack.guice.Guice;
>>>>>>> master
import ratpack.handling.Handler;
import ratpack.http.MutableHeaders;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

import java.util.Map;

<<<<<<< HEAD
=======
import static ratpack.groovy.Groovy.groovyTemplate;

>>>>>>> master
public class Server {

    public void startServer(Map<String, Handler> handlers) throws Exception {
        RatpackServer.start(server -> server
<<<<<<< HEAD
        .serverConfig(config -> config
        .onError(System.out::println)
        .baseDir(BaseDir.find())
        .env())
        .handlers(chain -> {
            for (Map.Entry<String, Handler> handler: handlers.entrySet()) {
                chain.get(handler.getKey(), ctx -> {
                    MutableHeaders headers = ctx.getResponse().getHeaders();
                    headers.set("Access-Control-Allow-Origin", "*");
                    headers.set("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
                    handler.getValue().handle(ctx);

                });
            }
        }));
=======
            .serverConfig(config -> config
            .onError(System.out::println)
            .baseDir(BaseDir.find())
            .env())
            .registry(Guice.registry(b -> b.module(TextTemplateModule.class, conf -> conf.setStaticallyCompile(true))))
            .handlers(chain -> {
                for (Map.Entry<String, Handler> handler: handlers.entrySet()) {
                    chain.prefix("api", api -> {
                        api.path(handler.getKey(), ctx -> {
                            MutableHeaders headers = ctx.getResponse().getHeaders();
                            headers.set("Access-Control-Allow-Origin", "*");
                            headers.set("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
                            handler.getValue().handle(ctx);
                        });
                    });
                }
                chain.get("", ctx -> ctx.render(groovyTemplate( "index.html")));
                chain.get(":view", ctx -> ctx.render(groovyTemplate( "index.html")));
                chain.files(f -> f.dir("public"));
            })
        );
>>>>>>> master
    }
}