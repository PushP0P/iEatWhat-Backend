package Managers;

import Workers.FoodData;
import Workers.FoodSearch;
import com.fasterxml.jackson.databind.ObjectMapper;
import iEatWhatEvents.Event;
import iEatWhatEvents.Response;

public class FoodSearchManager {
    private static ObjectMapper om = new ObjectMapper();

    public static Response SearchEvent(Event evt) throws Exception {
        String type = evt.getType().split(":")[1];
        switch (type) {
            case"TERMS":
                return Response.pack(
                    "Search results for " + evt.getPayload(),
                    FoodSearch.getByFoodTerm(evt.getPayload()),
                    evt.getType());
            case"REPORT":
                return Response.pack(
                    "Report found for" + evt.getPayload(),
                    FoodSearch.getReport(evt.getPayload()),
                    evt.getType()
                );
            case"DESCRIPTION":
                return Response.pack(
                        "Description found for " + evt.getPayload(),
                        FoodSearch.getDescription(evt.getPayload()),
                        evt.getType()
                );
            case"NUTRIENT_LIST":
                FoodData.retrieveNutrientList(evt.getPayload());
                return Response.pack(
                        "Nutrient found for " + evt.getPayload(),
                        FoodSearch.getNutrientList(),
                        evt.getType()
                );
        }
        return new Response(
                false,
                "Search Type Not Found: " + evt.getType(),
                "No Body",
                evt.getType()
        );
    }
}