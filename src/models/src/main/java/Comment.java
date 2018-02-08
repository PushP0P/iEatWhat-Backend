import java.util.Date;

public class Comment {
    private String id;
    private String author_id;
    private String text;
    private String topic;
    private String container_id;
    private Comment[] replies;
    private String slug;
    private java.util.Date created_on;
    private java.util.Date updated_last;

    public void Comment(){

    }

    public void Comment( String id, String author_id, String text,
                         String topic, String container_id, String slug
                         ) {
            this.setAuthor_id(author_id);
            this.setContainer_id(container_id);
            this.setId(id);
            this.setReplies(replies);
            this.setSlug(slug);
            this.setText(text);
            this.setTopic(topic);
    }

    private void setSlug(String slug) {
        this.slug = slug;
    }

    private void setReplies(Comment[] replies) {
        this.replies = replies;
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    private void setContainer_id(String container_id) {
        this.container_id = container_id;
    }

    private void setText(String text) {
        this.text = text;
    }

    private void setTopic(String topic) {
        this.topic = topic;
    }

    private void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    private void setUpdated_last(Date updated_last) {
        this.updated_last = updated_last;
    }

    public String getSlug() {
        return slug;
    }

    public String getId() {
        return id;
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

    public Comment[] getReplies() {
        return this.replies;
    }

    public java.util.Date getUpdated_last() {
        return this.updated_last;
    }

    public java.util.Date getCreated_on() {
        return this.created_on;
    }

    public void updateComment(String text) {
        this.text = text;
        this.setUpdated_last(new java.util.Date());
    }
}
