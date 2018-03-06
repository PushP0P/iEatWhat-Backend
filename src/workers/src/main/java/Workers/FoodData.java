package Workers;

import DBManager.DBManager;
import USDA.*;

import Utilities.HTTPSRequest;
import Utilities.URLBuilders;

import Utilities.XMLUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import iEatWhatModels.FoodItem;
import org.hibernate.Session;
import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.persistence.NoResultException;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FoodData {
    private static ObjectMapper om = new ObjectMapper();
    private static String USDAUrl = "https://api.nal.usda.gov/ndb";
    private static String apiKey = "JiiJJlr1FvAcye8lkFIJuy8dFjhZcP2x7PNBEcIQ";
    private static Session session = DBManager.getSession();

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
    public static Set<SearchItem> searchFoodUSDA(String searchTerms) throws ParserConfigurationException, SAXException, IOException {

        // todo Invalidating the stores

        Set<SearchItem> results = Collections.emptySet();
        NodeList items;

//        try {
//            SearchTermResult.retrieveByTerm(session, searchTerms);
//        } catch (NoResultException n) {
        System.out.println("no term?");
        String type = "search";
        Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("q", searchTerms);
        params.put("format", "xml");
        Document resultXML = HTTPSRequest
                .getXMLRequest(new URL(URLBuilders.buildBaseUSDAURL(USDAUrl, type)
                        + URLBuilders.getParamsString(params))
                );
        resultXML.getDocumentElement().normalize();
        items = resultXML.getDocumentElement().getElementsByTagName("item");
        List<Node> nodeList = XMLUtil.asList(items);
        for (int i = 0; i < nodeList.size(); i++) {
            String ndbno = nodeList.get(i).getChildNodes().item(0).getTextContent();
            String name = nodeList.get(i).getChildNodes().item(1).getTextContent();
            String group = nodeList.get(i).getChildNodes().item(2).getTextContent();
            results.add(new SearchItem(ndbno, name, group));
        }
//        }
//        int id = SearchTermResult.add(session, searchTerms, results);
//        return session.find(SearchTermResult.class, id);

        return results;
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

        //        Report cachedReport = Report.


        // Declare vars for fetching a full USDA Report.
        String type = "V2/reports";
        Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("description_ndbno", ndbno);
        params.put("type", "f");
        params.put("format", "xml");

        // Generate endpoint
        URL reportURL = new URL(
            URLBuilders
                    .buildBaseUSDAURL(
                            USDAUrl, type
                    ) + URLBuilders
                    .getParamsString(
                            params
                    ));

        // Fetch
        Document report = HTTPSRequest
                .getXMLRequest(reportURL);
        report.getDocumentElement()
                .normalize();


        List<Node> items = XMLUtil.asList(report
                .getDocumentElement()
                .getElementsByTagName("food"));

        items.forEach(item -> {
            Description
                    .addOrUpdate(session, ((Element) item)
                            .getElementsByTagName("desc")
                            .item(0));
            List<Node> nutrientNodes = XMLUtil
                    .asList(((Element) item)
                    .getElementsByTagName("nutrients"));

            Set<Nutrient> nutrients = Collections.emptySet();

            // try conditional refactoring
            try {
                nutrients.add(Nutrient.retrieveById(session, ndbno));
            } catch (NoResultException n) {
                for (Node nutrient : nutrientNodes) {
                    if (nutrient != null) {
                        try {
                            nutrients.add(nodeToNutrient((Node) nutrients, "Betty"));
                            Nutrient.updateWithInstance(session, ((Node) nutrients).getAttributes().getNamedItem("ndbno").toString(), nutrients);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }

                    }
                }
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
     */
    public static NutrientList getNutrientListUSDA() throws IOException, ParserConfigurationException, SAXException {

        // Initialize Params for USDA
        Map<String, String> params = new HashMap<>();
        String type = "list";
        params.put("api_key", apiKey);
        params.put("max", "300");
        params.put("type", "f");
        params.put("lt", "n");
        params.put("format", "xml");

        // Fetch XML from USDA
        Document responseXML = HTTPSRequest.getXMLRequest(new URL(URLBuilders.buildBaseUSDAURL(USDAUrl, type) + URLBuilders.getParamsString(params)));
        responseXML.getDocumentElement().normalize();
        List<Node> nutrientNodes = XMLUtil.asList(responseXML.getElementsByTagName("item"));
        Set<Nutrient> nutrients = Collections.emptySet();

        // Group the nutrients into a Collection.class
        for (Node node : nutrientNodes) {
            if(node != null) {
                Nutrient nutrient = nodeToNutrient(node, "merp");
                nutrients.add(nutrient);
            }
        }

        Attributes attributes = (Attributes) responseXML.getDocumentElement().getAttributes();
        int total = Integer.parseInt(attributes.getValue("total"));
        String sr = attributes.getValue("sr");

        NutrientList.add(session, total, sr, nutrients);
        return NutrientList.getLatestList(session);
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
    public static NutrientList retrieveNutrientList() {
        NutrientList nutrientList = NutrientList.getLatestList(session);
        if (nutrientList != null) {
            return nutrientList;
        }
            return NutrientList.getLatestList(session);
    }

    public static FoodItem FoodItemFactory(String ndbno) throws
            ParserConfigurationException, SAXException, IOException {
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

    private static String extractUPC(String productName) {
        return productName.split("/(UPC:)+w/")[1];
    }

    private static String[] csvToArray(String productName) {
        return productName.split(",");
    }

    static String[] extractCapCased(String productName) {
        return productName.split("/([A-Z]){2}/g");
    }

    private static Nutrient nodeToNutrient(Node nutNode, String... args) throws JsonProcessingException {
            System.out.println("nutrient \n" + om.writeValueAsString(nutNode));
        if (nutNode != null) {


            System.out.println("nutrient \n" + om.writeValueAsString(nutNode));
            // Set our nutrient node values to Nutrient.class
            String nutrient_id = ((Element) nutNode).getElementsByTagName("nutrient_id").item(0).getTextContent();
            String derivation = ((Element) nutNode).getElementsByTagName("derivation").item(0).getTextContent();
            String group = ((Element) nutNode).getElementsByTagName("group").item(0).getTextContent();
            String name = ((Element) nutNode).getElementsByTagName("name").item(0).getTextContent();
            String se = ((Element) nutNode).getElementsByTagName("se").item(0).getTextContent();
            String sourcecode = ((Element) nutNode).getElementsByTagName("sourcecode").item(0).getTextContent();
            String unit = ((Element) nutNode).getElementsByTagName("unit").item(0).getTextContent();
            int dp = Integer.parseInt(((Element) nutNode).getElementsByTagName("dp").item(0).getTextContent());
            float value = Float.parseFloat(((Element) nutNode).getElementsByTagName("value").item(0).getTextContent());

            //
            return new Nutrient(nutrient_id, derivation, dp, group, name, se, sourcecode, unit, value);
        }
        return new Nutrient("Null", "Null", -1, "Null", "Null", "Null","Null","Null", -1);
    }
}
