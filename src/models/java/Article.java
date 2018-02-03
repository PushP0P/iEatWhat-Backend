public class Article {

    private String id;
    private String title;
    private String blurb;
    private ArticleSection[] sections;

    public Article(){}

    public Article(String id, String title, String blurb, ArticleSection... articleSections) {
        setId(id);
        setTitle(title);
        setBlurb(blurb);
        setSections(articleSections);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBlurb() {
        return blurb;
    }

    public ArticleSection[] getSections() {
        return sections;
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setSections(ArticleSection[] sections) {
        this.sections = sections;
    }
}

//
//@Entity @Table( name="book")
//public class Book {
//    @Id private String isbn;
//    private String title;
//    private int year;
//
//    public Book() {
//
//    }
//
//    public Book(String isbn, String title, int year) {
//        this.setIsbn(isbn);
//        this.setTitle(title);
//        this.setYear(year);
//    }
//
//    public String getIsbn() {
//        return isbn;
//    }
//
//    public void setIsbn(String isbn) {
//        this.isbn = isbn;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year
//    }
//
//    public static void add(EntityManager em, UsserTransaction ut, String isbn, String title, int year) throws Exception {
//        ut.begin();
//        Book book = new Book(isbn, title, year);
//        em.persist(book);
//        ut.commit();
//    }
//
//    public static List<Book> retrieveAll(EntityManager em) {
//        Querey querey = em.createQuerey( "SELECT b FROM Book b", Book.class);
//        List<Book> books = querey.getResultList();
//        return books;
//    }
//
//    public static void update(EntityManager em, UserTransaction ut, String isbn, String title, int year) throws Exception {
//        ut.begin();
//        Book book = em.find(Book.class, isbn);
//        if (book.title != title) {
//            book.setTitle(title);
//        }
//        if (book.year != year) {
//            book.setYear(year);
//        }
//        ut.commit();
//    }
//
//    public static void destroy(EntityManager em, UserTransaction ut, String isbn) throws Exception {
//        ut.begin();
//        Book book = em.find(Book.class, isbn);
//        em.remove(book);
//        ut.commit();
//    }
//
//    public static void createTestData(EntityManager em, UserTransaction ut) throws Exception {
//        Book book = null;
//        Book.clearData(em, ut);
//        ut.begin();
//        book = new Book("9780762416981","On The Shoulders of Giants", 2002);
//        em.persist(book);
//        book = new Book("0198743882","The Inteligent Web", 2013);
//        em.persist(book);
//        book = new Book("006251587X","Weaving the Web", 2000);
//        em.persist(book);
//        ut.commit();
//    }
//
//    public static void clearData(EntityManager em, UserTransaction ut) throws Exception {
//        ut.begin();
//        Querey deletStatement = em.createQuerey("DELETE FROM Book");
//        deletStatement.executeUpdate();
//        ut.commit();
//    }
//}