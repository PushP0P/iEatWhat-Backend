package USDA;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.Map;

//@Entity @Table(name = "DESCRIPTION")
public class Description {
//    @Id
    public String ndbno;
    public String key = "desc";
//    @OneToOne
//    @JoinColumn(name = "report_id")
    public Report report;
    public String name;
    public String sd;
    public String group;
    public String sn;
    public String cn;
    public String manu;
    public int nf;
    public int cf;
    public int ff;
    public int pf;
    public String r;
    public String rd;
    public String ds;
    public String ru;

    Description() {

    }

    public Description(String description_id, Report report, String ndbno, String name, String sd, String group, String sn, String cn, String manu, int nf, int cf, int ff, int pf, String r, String rd, String ds, String ru) {
        this.setReport(report);
        this.setNdbno(ndbno);
        this.setName(name);
        this.setSd(sd);
        this.setGroup(group);
        this.setSn(sn);
        this.setCn(cn);
        this.setManu(manu);
        this.setNf(nf);
        this.setCf(cf);
        this.setFf(ff);
        this.setPf(pf);
        this.setR(r);
        this.setRd(rd);
        this.setDs(ds);
        this.setRu(ru);
    }

    public String getKey() {
        return key;
    }

    private void setKey(String key) {
        this.key = key;
    }

    public Report getReport() {
        return report;
    }

    private void setReport(Report report) {
        this.report = report;
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

    public static void add(Session session, Transaction transaction, Report report, Map<String, Object> descriptionValues) {
        transaction.begin();
        Description description = new Description(
            (String) descriptionValues.get("ndbno"),
            report,
            (String) descriptionValues.get("ndbno"),
            (String) descriptionValues.get("name"),
            (String) descriptionValues.get("sd"),
            (String) descriptionValues.get("group"),
            (String) descriptionValues.get("sn"),
            (String) descriptionValues.get("cn"),
            (String) descriptionValues.get("manu"),
            (int) descriptionValues.get("nf"),
            (int) descriptionValues.get("cf"),
            (int) descriptionValues.get("ff"),
            (int) descriptionValues.get("pf"),
            (String) descriptionValues.get("r"),
            (String) descriptionValues.get("rd"),
            (String) descriptionValues.get("ds"),
            (String) descriptionValues.get("ru")
        );
        session.saveOrUpdate(description);
        transaction.commit();

    }
}
