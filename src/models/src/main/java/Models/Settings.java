package Models;

import javax.persistence.Column;
import javax.persistence.OneToMany;

public class Settings extends Model{

    // Would be different category tags?
    @OneToMany
    private String[] constraints;
    @Column
    private boolean google_linked;
    @Column
    private boolean twittter_linked;
    @Column
    private boolean local_linked;

    public Settings(){}

    public Settings(String[] constraints, boolean google_linked, boolean twittter_linked,
                         boolean local_linked){
        this.setConstraints(constraints);
        this.setGoogle_linked(google_linked);
        this.setLocal_linked(local_linked);
        this.setTwittter_linked(twittter_linked);
    }

    public void setConstraints(String[] constraints) {
        this.constraints = constraints;
    }

    public void setGoogle_linked(boolean google_linked) {
        this.google_linked = google_linked;
    }

    public void setTwittter_linked(boolean twittter_linked) {
        this.twittter_linked = twittter_linked;
    }

    public void setLocal_linked(boolean local_linked) {
        this.local_linked = local_linked;
    }

    public String[] getConstraints() {
        return constraints;
    }

    public boolean isGoogle_linked() {
        return google_linked;
    }

    public boolean isTwittter_linked() {
        return twittter_linked;
    }

    public boolean isLocal_linked() {
        return local_linked;
    }
}

