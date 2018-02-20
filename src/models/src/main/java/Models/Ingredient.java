package Models;

public class Ingredient {
    private String nutrient_id;
    private String name;
    private String description;
    private String group;

    public void Ingredient() {

    }

    public void Ingredient(String nutrient_id, String name, String description, String group) {

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setNutrient_id(String nutrient_id) {
        this.nutrient_id = nutrient_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getGroup() {
        return group;
    }

    public String getNutrient_id() {
        return nutrient_id;
    }
}
