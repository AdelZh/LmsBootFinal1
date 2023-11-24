package peaksoft.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lessonName;
    @ManyToOne
    private Course course;
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.REMOVE)
    private List<Task> task;


}
