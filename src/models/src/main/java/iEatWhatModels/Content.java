package iEatWhatModels;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.text.Document;
import java.util.Date;

@Entity
public class Content {
    @Id
    public String Content_Id;
    public Document markDown;
    @Temporal(TemporalType.DATE)
    public Date create_on;
    @Temporal(TemporalType.TIMESTAMP)
    public Date updated_on;


    public Content() {}

    public Content(String content_Id, Document markDown) {
        Content_Id = content_Id;
        this.markDown = markDown;
    }

    public String getContent_Id() {
        return Content_Id;
    }

    public void setContent_Id(String content_Id) {
        Content_Id = content_Id;
    }

    public Document getMarkDown() {
        return markDown;
    }

    public void setMarkDown(Document markDown) {
        this.markDown = markDown;
    }
}