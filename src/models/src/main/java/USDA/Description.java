package USDA;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Description {
    @Id
    public String ndbno;
    //    @OneToOne
//    @JoinColumn(name = "report_id")
//    public Report report;
    @Column
    public String name;
    @Column
    public String sd;
    @Column
    public String group;
    @Column
    public String sn;
    @Column
    public String cn;
    @Column
    public String manu;
    @Column
    public int nf;
    @Column
    public int cf;
    @Column
    public int ff;
    @Column
    public int pf;
    @Column
    public String r;
    @Column
    public String rd;
    @Column
    public String ds;
    @Column
    public String ru;

    public Description() {
    }

    public Description(String ndbno, String name, String manu) {
        this.setNdbno(ndbno);
        this.setName(name);
        this.setManu(manu);
    }

    public String getNdbno() {
        return ndbno;
    }

    private void setNdbno(String ndbno) {
        this.ndbno = ndbno;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getSd() {
        return sd;
    }

    private void setSd(String sd) {
        this.sd = sd;
    }

    public String getGroup() {
        return group;
    }

    private void setGroup(String group) {
        this.group = group;
    }

    public String getSn() {
        return sn;
    }

    private void setSn(String sn) {
        this.sn = sn;
    }

    public String getCn() {
        return cn;
    }

    private void setCn(String cn) {
        this.cn = cn;
    }

    public String getManu() {
        return manu;
    }

    private void setManu(String manu) {
        this.manu = manu;
    }

    public int getNf() {
        return nf;
    }

    private void setNf(int nf) {
        this.nf = nf;
    }

    public int getCf() {
        return cf;
    }

    private void setCf(int cf) {
        this.cf = cf;
    }

    public int getFf() {
        return ff;
    }

    private void setFf(int ff) {
        this.ff = ff;
    }

    public int getPf() {
        return pf;
    }

    private void setPf(int pf) {
        this.pf = pf;
    }

    public String getR() {
        return r;
    }

    private void setR(String r) {
        this.r = r;
    }

    public String getRd() {
        return rd;
    }

    private void setRd(String rd) {
        this.rd = rd;
    }

    public String getDs() {
        return ds;
    }

    private void setDs(String ds) {
        this.ds = ds;
    }

    public String getRu() {
        return ru;
    }

    private void setRu(String ru) {
        this.ru = ru;
    }

    public static Description add(Session session, Transaction transaction, Map<String, String> descriptionValues) {
        transaction.begin();
        System.out.println(" Description Values \n" + descriptionValues.toString());
        Description description = new Description() {
        };
        for (Map.Entry prop : descriptionValues.entrySet()) {
        }
        session.saveOrUpdate(description);
        transaction.commit();

        return description;
    }

    public static class Builder {
        public String ndbno = "no value";
        public String name = "no value";
        public String sd = "no value";
        public String group = "no value";
        public String sn = "no value";
        public String cn = "no value";
        public String manu = "no value";
        public int nf = -1;
        public int cf = -1;
        public int ff = -1;
        public int pf = -1;
        public String r = "no value";
        public String rd = "no value";
        public String ds = "no value";
        public String ru = "no value";

        public Builder withNdbno(String ndbno) {
            this.ndbno = ndbno;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSd(String sd) {
            this.sd = sd;
            return this;
        }

        public Builder withGroup(String group) {
            this.group = group;
            return this;
        }

        public Builder withSn(String sn) {
            this.sn = sn;
            return this;
        }

        public Builder withCn(String cn) {
            this.cn = cn;
            return this;
        }

        public Builder withManu(String manu) {
            this.manu = manu;
            return this;
        }

        public Builder withNf(int nf) {
            this.nf = nf;
            return this;
        }

        public Builder withCf(int cf) {
            this.cf = cf;
            return this;
        }

        public Builder withFf(int ff) {
            this.ff = ff;
            return this;
        }

        public Builder withPf(int pf) {
            this.pf = pf;
            return this;
        }

        public Builder withR(String r) {
            this.r = r;
            return this;
        }

        public Builder withRd(String rd) {
            this.rd = rd;
            return this;
        }

        public Builder withDs(String ds) {
            this.ds = ds;
            return this;
        }

        public Builder withRu(String ru) {
            this.ru = ru;
            return this;
        }

//        public Description build() {
//
//        }

    }


}
