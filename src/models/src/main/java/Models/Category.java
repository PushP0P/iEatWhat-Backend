package Models;

import javax.persistence.*;

@Entity
public class Category extends Model {
    @Column
    private String tag;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String icon;

    public Category() {

    }

    public Category(String name, String tag, String description, String icon) {
        this.setName(name);
        this.setTag(tag);
        this.setDescription(description);
        this.setIcon(icon);
    }

    @Override
    public String toString() {
        return "Category{" +
                "tag='" + tag + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getTag() {
        return tag;
    }
}
