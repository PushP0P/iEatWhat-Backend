package Models;

import javax.persistence.Column;
import javax.persistence.OneToMany;

public class ArticleSection extends Model {
    @Column
    private String subTitle;
    @OneToMany
    private String[] paragraphs;

    public ArticleSection() {}

    public ArticleSection(String subTitle, String... paragraphs) {
        setSubTitle(subTitle);
        setParagraphs(paragraphs);
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String[] getParagraphs() {
        return paragraphs;
    }

    private void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    private void setParagraphs(String[] paragraphs) {
        this.paragraphs = paragraphs;
    }

}
