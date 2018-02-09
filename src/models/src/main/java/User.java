public class User {
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String password_hash;
    private String google_refresh_token;
    private String twitter_refresh_token;
    private Settings settings;
    private java.util.Date create_on;
    private java.util.Date updated_last;
    private Enum role;
    private Comment[] comments;

    public void User(){

    }

    public void User( String id, String first_name, String last_name, String email,
                      String password_hash, String google_refresh_token, String twitter_refresh_token,
                      Settings settings){

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setGoogle_refresh_token(String google_refresh_token) {
        this.google_refresh_token = google_refresh_token;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public void setTwitter_refresh_token(String twitter_refresh_token) {
        this.twitter_refresh_token = twitter_refresh_token;
    }

    public Settings getSettings() {
        return settings;
    }

    public String getEmail() {
        return email;
    }

    public void setUpdated_last(java.util.Date updated_last) {
        this.updated_last = updated_last;
    }

    public void setCreate_on(java.util.Date create_on) {
        this.create_on = create_on;
    }

    public java.util.Date getUpdated_last() {
        return updated_last;
    }

    public String getId() {
        return id;
    }

    public java.util.Date getCreate_on() {
        return create_on;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getGoogle_refresh_token() {
        return google_refresh_token;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public String getTwitter_refresh_token() {
        return twitter_refresh_token;
    }
}
