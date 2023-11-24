package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Lesson;

public interface LessonRepo extends JpaRepository<Lesson, Long> {

   

}
