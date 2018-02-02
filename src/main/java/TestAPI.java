import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public class TestAPI {

    public static void main(String... args) {

    }

    @Path("/foo")
    public class TestPath {
        @GET
        @Produces("text/plain")
        public String testingTheFoo(){
            return "Hello API";
        }
    }
}
