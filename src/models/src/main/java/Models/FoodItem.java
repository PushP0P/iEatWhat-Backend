package Models;

public class FoodItem {
    private String id;
    private String name;
    private String isbn;
    private String[] category_tags;
    private String description;
    private String slug;
    private java.util.Date created_on;
    private java.util.Date updated_last;

    public void FoodItem() {

    }

    public void FoodItem(String id, String name, String isbn,
                         String[] category_tags, String description,
                         java.util.Date created_on, java.util.Date updated_last) {
        this.setId(id);
        this.setCategory_tags(category_tags);
        this.setDescription(description);
        this.setIsbn(isbn);
        this.setCreated_on(created_on);
        this.setSlug(slug);
        this.setUpdated_last(updated_last);
        this.setName(name);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory_tags(String[] category_tags) {
        this.category_tags = category_tags;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    private void setCreated_on(java.util.Date created_on) {
        this.created_on = created_on;
    }

    private void setUpdated_last(java.util.Date updated_last) {
        this.updated_last = updated_last;
    }

    public String getId() {
        return id;
    }

    public java.util.Date getCreated_on() {
        return created_on;
    }

    public java.util.Date getUpdated_last() {
        return updated_last;
    }

    public String getDescription() {
        return description;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String[] getCategory_tags() {
        return category_tags;
    }
}
