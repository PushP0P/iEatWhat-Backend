package Models;

import javax.persistence.Column;
import javax.persistence.OneToMany;

public class Article extends Model{
    @Column
    private String id;
    @Column
    private String title;
    @Column
    private String blurb;

    public Article(){}

    public Article(String id, String title, String blurb) {
        setId(id);
        setTitle(title);
        setBlurb(blurb);
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

    private void setId(String id) {
        this.id = id;
    }

    private void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    private void setTitle(String title) {
        this.title = title;
    }

}