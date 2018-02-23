package USDA;

import javax.persistence.*;

@Entity @Table(name = "DESCRIPTION")
public class Description {
    @Id
    public String description_id;
    public String key = "desc";
    @OneToOne
    @JoinColumn(name = "report_id")
    public Report report;
    public String ndbFoodNumber;
    public String foodName;
    public String shortDescription;
    public String foodGroup;
    public String scientificName;
    public String commercialName;
    public String manufacture;
    public String nitrogenToProteinConversionFactor;
    public String carbohydratFactor;
    public String fatFactor;
    public String proteinFactor;
    public String refuse;
    public String refuseDescription;
    public String databaseSource;
    public String reportingUnit;


}
