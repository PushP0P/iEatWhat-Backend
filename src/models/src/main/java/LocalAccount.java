public class LocalAccount {
    private String id;
    private String id_token;
    private String account_owner;

    private java.util.Date created_on;
    private java.util.Date updated_last;

    public void LocalAccount() {}

    public void LocalAccount(String id, String id_token, String account_owner, java.util.Date created_on) {
        this.setAccount_owner(account_owner);
        this.setId(id);
        this.setId_token(id_token);
        this.setCreated_on(created_on);
    }

    public void setId(String id) {
        this.id = id;
    }

    private void setAccount_owner(String account_owner) {
        this.account_owner = account_owner;
    }

    private void setId_token(String id_token) {
        this.id_token = id_token;
    }

    private void setCreated_on(java.util.Date created_on) {
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

}
