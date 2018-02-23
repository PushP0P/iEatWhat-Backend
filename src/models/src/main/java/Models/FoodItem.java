package Models;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="food_item")
public class FoodItem {
    @Id
    private String ndbno;
    @Column(name="name")
    private String name;
    private String upc;
    @OneToMany
    private List<Category> categories;
    private String description;
    @Temporal(TemporalType.DATE)
    private java.util.Date created_on;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updated_last;

    public FoodItem() {

    }

    public FoodItem(
        String ndbno, String name, String upc,
        String description, Date created_on, Date updated_last
    ) {
        this.setNdbno(ndbno);
//        this.setCategory_tags(category_tags);
        this.setDescription(description);
        this.setUPC(upc);
        this.setCreated_on(created_on);
        this.setUpdated_last(updated_last);
        this.setName(name);
    }

    public void setNdbno(String ndbno) {
        this.ndbno = ndbno;
    }

//    public void setCategory_tags(String[] category_tags) {
//        this.category_tags = category_tags;
//    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUPC(String isbn) {
        this.upc = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setCreated_on(java.util.Date created_on) {
        this.created_on = created_on;
    }

    private void setUpdated_last(java.util.Date updated_last) {
        this.updated_last = updated_last;
    }

    public String getNdbno() {
        return ndbno;
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

    public String getUPC() {
        return upc;
    }

    public String getName() {
        return name;
    }

//    public String[] getCategory_tags() {
//        return category_tags;
//    }

    public static List getAll(Session session) {
        Query query = session.createQuery( "from FoodItem", FoodItem.class);
        return query.getResultList();
    }

    public static void add(
            Session session, Transaction transaction, String ndbno, String upc,
            String name, String description
    ) {
        Timestamp timeNow = new Timestamp(System.currentTimeMillis());
        transaction.begin();
        FoodItem foodItem = new FoodItem(ndbno, name, upc, description, timeNow, timeNow);
        session.save(foodItem);
        transaction.commit();
    }
}

/**
 * package pl.model;

 @Entity @Table( name="book")
 public class Book {
 @Id private String isbn;
 private String title;
 private int year;

 public Book() {

 }

 public Book(String isbn, String title, int year) {
 this.setIsbn(isbn);
 this.setTitle(title);
 this.setYear(year);
 }

 public String getIsbn() {
 return isbn;
 }

 public void setIsbn(String isbn) {
 this.isbn = isbn;
 }

 public String getTitle() {
 return title;
 }

 public void setTitle(String title) {
 this.title = title;
 }

 public int getYear() {
 return year;
 }

 public void setYear(int year) {
 this.year = year;
 }

 public static void add(EntityManager em, UserTransaction ut, String isbn, String title, int year) throws Exception {
 ut.begin();
 Book book = new Book(isbn, title, year);
 em.persist(book);
 ut.commit();
 }

 public static List<Book> retrieveAll(EntityManager em) {
 Query query = em.createQuery( "SELECT b FROM Book b", Book.class);
 List<Book> books = query.getResultList();
 return books;
 }

 public static void update(EntityManager em, UserTransaction ut, String isbn, String title, int year) throws Exception {
 ut.begin();
 Book book = em.find(Book.class, isbn);
 if (book.title != title) {
 book.setTitle(title);
 }
 if (book.year != year) {
 book.setYear(year);
 }
 ut.commit();
 }

 public static void destroy(EntityManager em, UserTransaction ut, String isbn) throws Exception {
 ut.begin();
 Book book = em.find(Book.class, isbn);
 em.remove(book);
 ut.commit();
 }

 public static void createTestData(EntityManager em, UserTransaction ut) throws Exception {
 Book book = null;
 Book.clearData(em, ut);
 ut.begin();
 book = new Book("9780762416981","On The Shoulders of Giants", 2002);
 em.persist(book);
 book = new Book("0198743882","The Intelligent Web", 2013);
 em.persist(book);
 book = new Book("006251587X","Weaving the Web", 2000);
 em.persist(book);
 ut.commit();
 }

 public static void clearData(EntityManager em, UserTransaction ut) throws Exception {
 ut.begin();
 Querey deletStatement = em.createQuerey("DELETE FROM Book");
 deletStatement.executeUpdate();
 ut.commit();
 }
 }
 */