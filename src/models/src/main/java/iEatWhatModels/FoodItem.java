package iEatWhatModels;

import USDA.Description;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Entity
public class FoodItem {
    @Id
    private String foodItem_ndbno;
    private String name;
    private String manu;
    private String upc;
    @ManyToMany(mappedBy = "category_tag")
    private Set<Category> categories;
    @OneToOne(mappedBy = "description_ndbno")
    private Description description;
    private String blurb;
    @Temporal(TemporalType.DATE)
    private java.util.Date created_on;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updated_last;

    FoodItem() {

    }

    FoodItem(String foodItem_ndbno, String name, String manu, String upc, Set<Category> categories, Description description, String blurb) {
        this.foodItem_ndbno = foodItem_ndbno;
        this.name = name;
        this.manu = manu;
        this.upc = upc;
        this.categories = categories;
        this.description = description;
        this.blurb = blurb;
        this.created_on = new Date(System.currentTimeMillis());
    }

    public String getNdbno() {
        return foodItem_ndbno;
    }

    public void setNdbno(String ndbno) {
        this.foodItem_ndbno = ndbno;
    }

    public String getManu() {
        return manu;
    }

    public void setManu(String manu) {
        this.manu = manu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public Date getUpdated_last() {
        return updated_last;
    }

    public void setUpdated_last(Date updated_last) {
        this.updated_last = updated_last;
    }

    public static String add(Session session, String ndbno, String name, String manu, String upc, Set<Category> categories, Description description, String blurb){
        Transaction transaction = session.getTransaction();
        FoodItem foodItem = new FoodItem(ndbno, name, manu, upc, categories, description, blurb);
        String id = (String) session.save(foodItem);
        transaction.commit();
        return id;
    }

    public static FoodItem retrieveByNdbno(Session session, String ndbno) throws NoResultException {
        return session.find(FoodItem.class, ndbno);
    }

    public static class Builder {
        private String ndbno = "no value";
        private String name = "no value";
        private String upc = "no value";
        private Set<Category> categories = Collections.emptySet();
        private Description description = new Description();
        private String blurb = "no value";
        private String manu = "no value";

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

         public Builder withNdbno(String ndbno) {
            this.ndbno = ndbno;
            return this;
        }

        public Builder withUpc(String upc) {
            this.upc = upc;
            return this;
        }

        public Builder withManu(String manu) {
            this.manu = manu;
            return this;
        }

        public Builder withCategories(Set<Category> categories) {
            this.categories = categories;
            return this;
        }
        public Builder withDescription(Description description) {
            this.description = description;
            return this;
        }
        public Builder withBlurb(String blurb) {
            this.blurb = blurb;
            return this;
        }

        public FoodItem build() {
            return new FoodItem(this.ndbno, this.name, this.manu, this.upc, this.categories, this.description, this.blurb);
        }
    }
}

