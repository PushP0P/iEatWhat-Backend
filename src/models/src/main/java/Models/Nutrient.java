package Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="nutrient")
public class Nutrient {
    @Id
    private String id;
    private String name;
    private String description;
    private String group;

    public void Nutrient() {

    }

    public void Nutrient(String nutrient_id, String name, String description, String group) {

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

    public void setId(String nutrient_id) {
        this.id = nutrient_id;
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

    public String getId() {
        return id;
    }
}
