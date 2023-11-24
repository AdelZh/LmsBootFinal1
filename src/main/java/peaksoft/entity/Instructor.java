package peaksoft.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String specialization;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Company company;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Course course;

}




