package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Student;


@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
}
