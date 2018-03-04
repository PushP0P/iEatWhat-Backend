package USDA;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.w3c.dom.Node;

import javax.persistence.*;

import java.util.List;

@Entity
public class Description {
    @Id
    public String description_ndbno;
    //    @OneToOne
//    @JoinColumn(name = "report_id")
//    public Report report;
    @Column
    public String name;
    @Column
    public String sd;
    @Column
    public String fg;
    @Column
    public String sn;
    @Column
    public String cn;
    @Column
    public String manu;
    @Column
    public float nf;
    @Column
    public float cf;
    @Column
    public float ff;
    @Column
    public float pf;
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

    public Description(String description_ndbno, String name, String sd, String fg, String sn, String cn, String manu, float nf, float cf, float ff, float pf, String r, String rd, String ds, String ru) {
        this.description_ndbno = description_ndbno;
        this.name = name;
        this.sd = sd;
        this.fg = fg;
        this.sn = sn;
        this.cn = cn;
        this.manu = manu;
        this.nf = nf;
        this.cf = cf;
        this.ff = ff;
        this.pf = pf;
        this.r = r;
        this.rd = rd;
        this.ds = ds;
        this.ru = ru;
    }

    public String getDescription_ndbno() {
        return description_ndbno;
    }

    public void setDescription_ndbno(String description_ndbno) {
        this.description_ndbno = description_ndbno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getFg() {
        return fg;
    }

    public void setFg(String fg) {
        this.fg = fg;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getManu() {
        return manu;
    }

    public void setManu(String manu) {
        this.manu = manu;
    }

    public float getNf() {
        return nf;
    }

    public void setNf(float nf) {
        this.nf = nf;
    }

    public float getCf() {
        return cf;
    }

    public void setCf(float cf) {
        this.cf = cf;
    }

    public float getFf() {
        return ff;
    }

    public void setFf(float ff) {
        this.ff = ff;
    }

    public float getPf() {
        return pf;
    }

    public void setPf(float pf) {
        this.pf = pf;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    public static List<Description> retrieveAll(Session session) {
        Query query = session.createQuery("select d from Description d", Description.class);
        List<Description> descriptions = query.getResultList();
        return descriptions;
    }

    public static Description findDescription(Session session, String ndbno) {
        return session.find(Description.class, ndbno);
    }

    public static void addOrUpdate(Session session, Node descNode) {
        // Set values from the XMLNode
        String ndbno = descNode.getAttributes().getNamedItem("description_ndbno").getNodeValue();
        String name = descNode.getAttributes().getNamedItem("name").getNodeValue();
        String sd = descNode.getAttributes().getNamedItem("sd").getNodeValue();
        String group = descNode.getAttributes().getNamedItem("fg").getNodeValue();
        String sn = descNode.getAttributes().getNamedItem("sn").getNodeValue();
        String cn = descNode.getAttributes().getNamedItem("cn").getNodeValue();
        String manu = descNode.getAttributes().getNamedItem("manu").getNodeValue();
        float nf = Float.parseFloat(descNode.getAttributes().getNamedItem("nf").getNodeValue());
        float cf = Float.parseFloat(descNode.getAttributes().getNamedItem("cf").getNodeValue());
        float ff = Float.parseFloat(descNode.getAttributes().getNamedItem("ff").getNodeValue());
        float pf = Float.parseFloat(descNode.getAttributes().getNamedItem("pf").getNodeValue());
        String r = descNode.getAttributes().getNamedItem("r").getNodeValue();
        String rd = descNode.getAttributes().getNamedItem("rd").getNodeValue();
        String ds = descNode.getAttributes().getNamedItem("ds").getNodeValue();
        String ru = descNode.getAttributes().getNamedItem("ru").getNodeValue();

        // start transaction
        Transaction transaction = session.getTransaction();
        transaction.begin();

        // Create new instance of our model with the values
        Description description = new Description(ndbno, name, sd, group, sn, cn, manu, nf, cf, ff, pf, r, rd, ds, ru);

        // save or update with hibernate
        session.saveOrUpdate(description);

        // commit/flush
        transaction.commit();

    }

    public static class Builder {
        public String ndbno = "no value";
        public String name = "no value";
        public String sd = "no value";
        public String group = "no value";
        public String sn = "no value";
        public String cn = "no value";
        public String manu = "no value";
        public float nf = -1;
        public float cf = -1;
        public float ff = -1;
        public float pf = -1;
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

        public Builder withNf(float nf) {
            this.nf = nf;
            return this;
        }

        public Builder withCf(float cf) {
            this.cf = cf;
            return this;
        }

        public Builder withFf(float ff) {
            this.ff = ff;
            return this;
        }

        public Builder withPf(float pf) {
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

        public Description build() {
            return new Description(
                    this.ndbno,
                    this.name,
                    this.sd,
                    this.group,
                    this.sn,
                    this.cn,
                    this.manu,
                    this.nf,
                    this.cf,
                    this.ff,
                    this.pf,
                    this.r,
                    this.rd,
                    this.ds,
                    this.ru
            );
        }

    }

}
