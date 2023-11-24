package peaksoft.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private int duration;
    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<Group> group;
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<Lesson> lesson;
    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Instructor> instructors;


}
