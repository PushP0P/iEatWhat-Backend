package Models;

public class TwitterAccount {
    private String id;
    private String id_token;
    private String account_owner;
    private String refresh_token;
    private java.util.Date created_on;
    private java.util.Date updated_last;

    public void TwitterAccount() {}

    public void TwitterAccount(String id, String id_token, String account_owner, String refresh_token, java.util.Date created_on) {
        this.setAccount_owner(account_owner);
        this.setId(id);
        this.setId_token(id_token);
        this.setRefresh_token(refresh_token);
        this.setCreated_on(created_on);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccount_owner(String account_owner) {
        this.account_owner = account_owner;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public void setCreated_on(java.util.Date created_on) {
        this.created_on = created_on;
    }

    public void setUpdated_last(java.util.Date updated_last) {
        this.updated_last = updated_last;
    }

    public java.util.Date getUpdated_last() {
        return updated_last;
    }

    public java.util.Date getCreated_on() {
        return created_on;
    }

    public String getId() {
        return id;
    }

    public String getAccount_owner() {
        return account_owner;
    }

    public String getId_token() {
        return id_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }
}
