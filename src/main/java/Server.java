import Models.Model;
import com.fasterxml.jackson.databind.ObjectMapper;
import ratpack.groovy.template.TextTemplateModule;
import ratpack.guice.Guice;
import ratpack.handling.Chain;
import ratpack.handling.Handler;
import ratpack.hikari.HikariModule;
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
                b.module(HikariModule.class, hikariConfig -> {
                    hikariConfig.setDriverClassName("org.postgresql.Driver");
                    hikariConfig.addDataSourceProperty("URL", "jdbc:postgresql://localhost:5432/testdb");
//                    hikariConfig.setUsername("postgres");
//                    hikariConfig.setPassword("admin");
                    hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/testdb");
                }).bind(DBManager.class);
            }))
            .handlers(chain -> {
                chain.prefix("get", api -> {
                    headers(getHandlers, api);
                });
                chain.prefix("post", api -> {
                    headers(postHandlers, api);
                });
                chain.get("", ctx -> ctx.render(groovyTemplate( "index.html")));
                chain.get("testdb", ctx -> {
                    ObjectMapper om = new ObjectMapper();
                    DBManager test = new DBManager();
                    test.handle(ctx);
                    Model model = new Model();
                    test.insert(model);
                    /*String columns[] = {"username", "string", "password", "string"};
                    String data[] = { "daniel", "pass"};
                    test.createTable("users", columns);
                    test.deleteAllFromTable("users");
                    test.insertIntoTable("users", data);
                    String results[] = test.readAllFromTable("users");
                    String result = "";
                    for (String i:results) {
                        result += i + " ";
                    }*/
                    ctx.render(om.writeValueAsString(model));
                });
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