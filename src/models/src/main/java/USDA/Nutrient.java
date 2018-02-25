package USDA;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Nutrient {
    @Id
//    @ManyToMany
    public String nutrient_number;
//    @OneToMany(mappedBy = "nutrient")
//    public Set<Measures> measures;
    @Column
    public String nutrientName;
    @Column
    public String listOfSourceId;
    @Column
    public String derived;
    @Column
    public String unit;
    @Column
    public String equivalent100g;
    @Column
    public String dataPointCount;
    @Column
    public String standardError;
}
