package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@Table(name = "companies")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String  locatedCountry;
    private String image;
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "company")
    private List<Course> course;
    @OneToMany(mappedBy = "company")
    private List<Instructor> instructors;
    @OneToMany(mappedBy = "company")
    private List<Student> students;
    @OneToMany(mappedBy = "company")
    private List<Group> groups;

}
