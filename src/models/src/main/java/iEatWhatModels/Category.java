package iEatWhatModels;

import USDA.Nutrient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    private String tag;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String icon;
    @ManyToMany(mappedBy = "nutrient_id")
    private Set<Nutrient> qualifiers;
    @Column
    private boolean readOnly;

    public Category() {

    }

    public Category(String tag, String name, String description, String icon, Set<Nutrient> qualifiers, boolean readOnly) {
        this.tag = tag;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.qualifiers = qualifiers;
        this.readOnly = readOnly;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Set<Nutrient> getQualifiers() {
        return qualifiers;
    }

    public void setQualifiers(Set<Nutrient> qualifiers) {
        this.qualifiers = qualifiers;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }


    public static String add(Session session, String tag, String name, String description, String icon, Set<Nutrient> qualifiers, boolean readOnly){
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Category category = new Category(tag, name, description, icon, qualifiers, readOnly);
        String result = (String) session.save(category);
        transaction.commit();
        return result;
    }

    public static int destroyByTagName(Session session, String tag) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Category category = session.find(Category.class, tag);
        session.remove(category);
        transaction.commit();
        return 1;
    }

    public static int updateInfo(Session session, String tag, String name, String description, String icon) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Category category = session.find(Category.class, tag);
        category.setDescription(description);
        category.setIcon(icon);
        category.setName(name);
        session.update(category);
        transaction.commit();
        return 1;
    }

    public static int addQualifiers(Session session, String tag, Set<Nutrient> newQualifiers) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Category category = session.find(Category.class, tag);
        Set<Nutrient> qualifiers = category.getQualifiers();
        boolean result = qualifiers.addAll(newQualifiers);
        if (commitIfSuccess(session, transaction, category, qualifiers, result)) return 1;
        return -1;
    }

    public static int removeQualifiers(Session session, String tag, Set<Nutrient> newQualifiers) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Category category = session.find(Category.class, tag);
        Set<Nutrient> qualifiers = category.getQualifiers();
        boolean result = qualifiers.removeAll(newQualifiers);
        if (commitIfSuccess(session, transaction, category, qualifiers, result)) return 1;
        return -1;
    }

    private static boolean commitIfSuccess(Session session, Transaction transaction, Category category, Set<Nutrient> qualifiers, boolean result) {
        if (result) {
            category.setQualifiers(qualifiers);
            session.update(category);
            transaction.commit();
            return true;
        }
        return false;
    }
}
