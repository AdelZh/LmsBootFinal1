package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Instructor;


@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Long> {
}
