package USDA;

import javax.persistence.*;

@Entity
public class Measures {
    @Id
    public String measure_id;
    @Column
    public String name;
    @Column
    public String eUnitEquivalent;
    @Column
    public String equivalentUnit;
    @Column
    public String gramEquivalent;
    @Column
    public String referenceSource;
}