package USDA;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name = "REPORT")
public class Report {
    @Id
    public String report_id;
    @OneToOne(mappedBy = "report")
    public Description description;
    @OneToMany(mappedBy = "report")
    public Set<Nutrient> nutrients;
}
