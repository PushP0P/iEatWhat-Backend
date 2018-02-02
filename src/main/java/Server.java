import ratpack.handling.Context;
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
                   handler.getValue().getHandler(ctx);
                });
            }
        }));
    }
}
