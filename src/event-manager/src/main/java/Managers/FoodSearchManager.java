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
            case"REPORT":
                String result = om.writeValueAsString(FoodSearch.getReport(evt.getPayload()));
                return  new Response(
                        true,
                        "Report Found",
                        result,
                        evt.getType()
                );
            case"DESCRIPTION":
                String description = om.writeValueAsString(FoodSearch.getDescription(evt.getPayload()));
                return  new Response(
                        true,
                        "Description Found",
                        description,
                        evt.getType()
                );
            case"NUTRIENT":
                FoodData.retrieveNutrient(evt.getPayload());
                return  new Response(
                        true,
                        "Description Found",
                        "Test Nutrient Search",
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