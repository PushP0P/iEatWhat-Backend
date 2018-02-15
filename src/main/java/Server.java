import ratpack.groovy.template.TextTemplateModule;
import ratpack.guice.Guice;
import ratpack.handling.Handler;
import ratpack.hikari.HikariModule;
import ratpack.http.MutableHeaders;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;
import java.sql.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;

import static ratpack.groovy.Groovy.groovyTemplate;

public class Server {

    public void startServer(Map<String, Handler> handlers) throws Exception {
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
                    hikariConfig.setUsername("postgres");
                    hikariConfig.setPassword("admin");
                    hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/testdb");
                }).bind(DBManager.class);
            }))
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
                //chain.get(":view", ctx -> ctx.render(groovyTemplate( "index.html")));
                chain.get("testdb", ctx -> {
                            DBManager test = new DBManager();
                            test.handle(ctx);
                            String columns[] = {"username", "string", "password", "string"};
                            String data[] = { "daniel", "pass"};
                            test.createTable("users", columns);
                            test.deleteAllFromTable("users");
                            test.insertIntoTable("users", data);
                            String results[] = test.readAllFromTable("users");
                            String result = "";
                            for (String i:results) {
                                result += i + " ";
                            }
                            ctx.render(result);
                            /*Connection c = ctx.get(DataSource.class).getConnection();
                            Statement statement = c.createStatement();
                            String update, insert, query, results = "";
                            update = "CREATE TABLE IF NOT EXISTS testTable ( name varchar(256))";
                            insert = "INSERT INTO testTable VALUES ( name 'Daniel' );";
                            query = "SELECT * FROM testTable;";
                            statement.executeUpdate(update);
                            statement.executeUpdate(insert);
                            ResultSet rs = statement.executeQuery(query);
                            while (rs.next()) {
                                results = rs.getString(1);
                            }
                            ctx.render(results);*/
                        });
                //chain.path("test", ctx -> {ctx.getResponse() });
                chain.get(":view", ctx -> ctx.render(groovyTemplate( "index.html")));
                chain.files(f -> f.dir("public"));
            })
        );
    }
}