public class Category {

    private String id;
    private String tag;
    private String name;
    private String description;
    private String icon;

    public void Category() {

    }

    public void Category(String id, String name, String tag,
                         String description, String icon) {
        this.setId(id);
        this.setName(name);
        this.setTag(tag);
        this.setDescription(description);
        this.setIcon(icon);
    }

    public void setId(String id) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public String getTag() {
        return tag;
    }
}
