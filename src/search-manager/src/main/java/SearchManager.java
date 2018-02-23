import Models.FoodItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchManager {
    private static ObjectMapper om =  new ObjectMapper();
    private static String USDAUrl = "https://api.nal.usda.gov/ndb";

    public SearchManager() {

    }

    public static HashMap searchFood(String searchTerms) throws IOException {
        Map<String, String> params = new HashMap<>();
        String apiKey = "JiiJJlr1FvAcye8lkFIJuy8dFjhZcP2x7PNBEcIQ";
        params.put("api_key", apiKey);
        params.put("q", searchTerms);
        params.put("format", "json");
        return om.readValue(HTTPSRequest.getRequest(new URL( buildBaseURL(USDAUrl, "search") +  ParameterURLBuilder.getParamsString(params))), HashMap.class);
    }

    private static String buildBaseURL(String base, String type) {
        return base + "/" + type + "?";
    }

    public static void updateDBWithResults() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        FoodItem.add(session, transaction,"1234", "foo123142", "Test", "lorem ipsum" );
        List<FoodItem> foodItemList = FoodItem.getAll(session);

       SearchManager searchManager = new SearchManager();
    }
}
