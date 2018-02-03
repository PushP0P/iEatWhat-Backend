import ratpack.handling.Context;
import ratpack.http.MutableHeaders;
import ratpack.http.Response;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

import java.util.Map;

public class Server {

    public interface GetHandler {
        void getHandler(Context ctx);
    }

    public void startServer(Map<String, GetHandler> handlers) throws Exception {
        RatpackServer.start(server -> server
        .serverConfig(config -> config
        .baseDir(BaseDir.find())
        .env())
        .handlers(chain -> {
            for (Map.Entry<String, GetHandler> handler: handlers.entrySet()) {
                chain.get(handler.getKey(), ctx -> {
                    MutableHeaders headers = ctx.getResponse().getHeaders();
                    headers.set("Access-Control-Allow-Origin", "*");
                    headers.set("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
                    handler.getValue().getHandler(ctx);
                });
            }
        }));
    }
}
