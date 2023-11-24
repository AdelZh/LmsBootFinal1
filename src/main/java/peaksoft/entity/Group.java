package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "groups")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private LocalDate dateOfStart;
    private String image;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Course course;
    @OneToMany(mappedBy = "group")
    private List<Student> student;
    @ManyToOne
    private Company company;






}
