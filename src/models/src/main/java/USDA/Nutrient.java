package USDA;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name="NUTRIENT")
public class Nutrient {
    @Id
    public String nutrient_number;
    @ManyToOne
    @JoinColumn(name = "report_id")
    public Report report;
    @OneToMany(mappedBy = "nutrient")
    public Set<Measures> measures;
    public String nutrientName;
    public String listOfSourceId;
    public String derived;
    public String unit;
    public String equivalent100g;
    public String dataPointCount;
    public String standardError;
}
