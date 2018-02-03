import ratpack.handling.Handler;
import ratpack.http.MutableHeaders;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

import java.util.Map;

public class Server {

    public void startServer(Map<String, Handler> handlers) throws Exception {
        RatpackServer.start(server -> server
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
    }
}

