package USDA;

import javax.persistence.*;

//@Entity @Table(name = "MEASURES")
public class Measures {
//    @Id
    public String measure_id;
    public String name;
//    @ManyToOne
//    @JoinColumn(name = "nutrientNumber")
    public Nutrient nutrient;
    public String eUnitEquivalent;
    public String equivalentUnit;
    public String gramEquivalent;
    public String referenceSource;
}