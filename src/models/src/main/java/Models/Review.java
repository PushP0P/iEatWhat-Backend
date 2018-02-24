package Models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Review extends Model {
    @Column
    private String author_id;
    @Column
    private String text;
    @Column
    private String topic;
    @Column
    private String container_id;
    @Column
    private String slug;

    public Review(){

    }

    public Review( String author_id, String text,
                         String topic, String container_id, String slug
                         ) {
            this.setAuthor_id(author_id);
            this.setContainer_id(container_id);
            this.setSlug(slug);
            this.setText(text);
            this.setTopic(topic);
    }


    @Override
    public String toString() {
        return "Review{" +
                "author_id='" + author_id + '\'' +
                ", text='" + text + '\'' +
                ", topic='" + topic + '\'' +
                ", container_id='" + container_id + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }


    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public void setContainer_id(String container_id) {
        this.container_id = container_id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSlug() {
        return slug;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public String getContainer_id() {
        return container_id;
    }

    public String getText() {
        return text;
    }

    public String getTopic() {
        return topic;
    }

    public void updateComment(String text) {
        this.text = text;
    }
}
