package Workers;

import DBManager.DBManager;
import USDA.Description;
import USDA.Nutrient;
import USDA.Report;

import USDA.SearchItem;
import Utilities.HTTPSRequest;
import Utilities.URLBuilders;

import Utilities.XMLUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import iEatWhatModels.FoodItem;
import iEatWhatModels.SearchTermResult;
import org.hibernate.Session;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import sun.misc.Regexp;

import javax.persistence.NoResultException;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FoodData {
    private static ObjectMapper om = new ObjectMapper();
    private static String USDAUrl = "https://api.nal.usda.gov/ndb";
    private static String apiKey = "JiiJJlr1FvAcye8lkFIJuy8dFjhZcP2x7PNBEcIQ";

    /**
     * searchFoodUSDA returns stored search results that match searchTerm or queries the USDA
     * search API to then cache and return the results.
     *
     * @param searchTerms
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static SearchTermResult searchFoodUSDA(String searchTerms) throws ParserConfigurationException, SAXException, IOException {
        List<SearchItem> results = Collections.emptyList();
        NodeList items;
        Session session = DBManager.getSession();

        try {
            SearchTermResult.checkForCached(session, searchTerms);
        } catch (NoResultException n) {
            String type = "search";
            Map<String, String> params = new HashMap<>();
            params.put("api_key", apiKey);
            params.put("q", searchTerms);
            params.put("format", "xml");
            Document resultXML = HTTPSRequest
                    .getXMLRequest(
                            new URL(URLBuilders.buildBaseUSDAURL(USDAUrl, type)
                                    + URLBuilders.getParamsString(params))
                    );
            resultXML.getDocumentElement().normalize();
            items = resultXML.getDocumentElement().getElementsByTagName("item");
            for (Node item : XMLUtil.asList(items)) {
                String ndbno = item.getChildNodes().item(0).getTextContent();
                String name = item.getChildNodes().item(1).getTextContent();
                String group = item.getChildNodes().item(2).getTextContent();
                results.add(new SearchItem(ndbno, name, group));
            }
        }
        int id = SearchTermResult.add(session, searchTerms, results);
        return session.find(SearchTermResult.class, id);
    }

    /**
     * getFullReport returns a cached or fetches, caches and returns a FullUSDA Report.
     *
     * @param ndbno
     * @return
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static Report getFullReportUSDA(String ndbno) throws IOException, ParserConfigurationException, SAXException {
        Session session = DBManager.getSession();
        String type = "V2/reports";
        Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("description_ndbno", ndbno);
        params.put("type", "f");
        params.put("format", "xml");
        URL reportURL = new URL(URLBuilders.buildBaseUSDAURL(USDAUrl, type) + URLBuilders.getParamsString(params));
        Document report = HTTPSRequest.getXMLRequest(reportURL);
        report.getDocumentElement().normalize();
        List<Node> items = XMLUtil.asList(report.getDocumentElement().getElementsByTagName("food"));
        items.forEach(item -> {
            Description.addOrUpdate(session, ((Element) item).getElementsByTagName("desc").item(0));
            List<Node> nutrientNodes = XMLUtil.asList(((Element) item).getElementsByTagName("nutrients"));
            Set<Nutrient> nutrients = Collections.emptySet();
            try {
                nutrients.add(Nutrient.retrieveById(session, ndbno));
            } catch (NoResultException n) {
                addNutrientsFromList(session, nutrientNodes);
            }

            System.out.println("Node Name \n" + report.getDocumentElement().getNodeName());
            Report.add(session, ndbno, Description.findDescription(session, ndbno), nutrients);
        });
        return session.find(Report.class, ndbno);
    }

    /**
     * getNutrientListUSDA will fetch the full nutrient list from the USDA API.
     *
     * @return
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static Document getNutrientListUSDA() throws IOException, ParserConfigurationException, SAXException {
        String type = "list";
        Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("max", "300");
        params.put("lt", "n");
        params.put("format", "xml");
        Document list = HTTPSRequest.getXMLRequest(new URL(URLBuilders.buildBaseUSDAURL(USDAUrl, type) + URLBuilders.getParamsString(params)));
        list.getDocumentElement().normalize();
        return list;
    }

    /**
     * retrieveDescription Extracts description from a getFullReport().
     *
     * @param ndbno
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Description retrieveDescription(String ndbno) throws ParserConfigurationException, SAXException, IOException {
        Session session = DBManager.getSession();
        Description description = Description.findDescription(session, ndbno);
        System.out.println("descNode \n" + om.writeValueAsString(description));
        if (description != null) {
            return description;
        }
        Report report = getFullReportUSDA(ndbno);
        return report.getDescription();
    }

    /**
     * updateNutrientList Updates the store's nutrient list
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static void updateNutrientList() throws ParserConfigurationException, SAXException, IOException {
        Document results = getNutrientListUSDA();
        List<Node> nutrients = XMLUtil.asList(results.getDocumentElement().getElementsByTagName("item"));

        Session session = DBManager.getSession();
        addNutrientsFromList(session, nutrients);
    }

    public static void addNutrientsFromList(Session session, List<Node> nutrients) {
        for (Node nutrient : nutrients) {
            String nutrient_id = ((Element) nutrient.getChildNodes().item(0)).getElementsByTagName("nutrient_id").item(0).getTextContent();
            String derivation = ((Element) nutrient.getChildNodes().item(0)).getElementsByTagName("derivation").item(0).getTextContent();
            int dp = Integer.parseInt(((Element) nutrient.getChildNodes().item(0)).getElementsByTagName("dp").item(0).getTextContent());
            String group = ((Element) nutrient.getChildNodes().item(0)).getElementsByTagName("group").item(0).getTextContent();
            String measures = ((Element) nutrient.getChildNodes().item(0)).getElementsByTagName("measures").item(0).getTextContent();
            String name = ((Element) nutrient.getChildNodes().item(0)).getElementsByTagName("name").item(0).getTextContent();
            String se = ((Element) nutrient.getChildNodes().item(0)).getElementsByTagName("se").item(0).getTextContent();
            String sourcecode = ((Element) nutrient.getChildNodes().item(0)).getElementsByTagName("sourcecode").item(0).getTextContent();
            String unit = ((Element) nutrient.getChildNodes().item(0)).getElementsByTagName("unit").item(0).getTextContent();
            float value = Float.parseFloat(((Element) nutrient.getChildNodes().item(0)).getElementsByTagName("value").item(0).getTextContent());
            Nutrient.addOrUpdate(session, nutrient_id, derivation, dp, group, measures, name, se, sourcecode, unit, value);
        }

    }

    public static void retrieveNutrient(String nutrient_id) throws ParserConfigurationException, SAXException, IOException {
        // Todo
        updateNutrientList();
    }

    public static FoodItem FoodItemFactory(String ndbno) throws ParserConfigurationException, SAXException, IOException {
        Session session = DBManager.getSession();
        Report report = getFullReportUSDA(ndbno);

        try {
            return FoodItem.retrieveByNdbno(session, ndbno);

        } catch (NoResultException n) {
            FoodItem.Builder foodItemBuilder = new FoodItem.Builder();
            foodItemBuilder.withNdbno(ndbno);
            Description description = report.getDescription();
            String[] values = csvToArray(description.name);
            switch (description.getFg().toLowerCase()) {
                case "american indian/alaska native foods":

                case "baby foods":

                case "branded food products database":
                    foodItemBuilder.withUpc(extractUPC(description.name));
                    if (values.length > 2) {
                        foodItemBuilder.withManu(values[1]);
                        foodItemBuilder.withName(values[1]);
                        return foodItemBuilder.build();
                    } else {
                        foodItemBuilder.withName(values[0]);

                    }

                case "baked products":

                case "beef products":

                case "beverages":

                case "breakfast cereals":

                case "cereal grains and pasta":

                case "dairy and egg products":

                case "fast foods":

                case "fats and oils":

                case "finfish and shellfish products":

                case "fruits and fruit juices":

                case "lamb, veal, and game products":

                case "legumes and legume products":

                case "meals, entrees, and side dishes":

                case "nut and seed products":

                case "pork products":

                case "poultry products":

                case "restaurant foods":

                case "sausages and luncheon meats":

                case "snacks":

                case "soups, sauces, and gravies":

                case "spices and herbs":

                case "sweets":

                case "vegetables and vegetable products":

                default:
                    return foodItemBuilder.build();

            }
        }
    }
    static String extractUPC(String productName){
        return productName.split("/(UPC:)+w/")[1];
    }

    static String[] csvToArray (String productName){
        return productName.split(",");
    }
    static String[] extractCapCased (String productName){
        return productName.split("/([A-Z]){2}/g");
    }
}
