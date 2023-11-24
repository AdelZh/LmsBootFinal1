package peaksoft.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;


@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    @Modifying
    @Query("UPDATE Instructor i SET i.course = null WHERE i.course = :course")
    void updateInstructorsForDeletedCourse(@Param("course") Course course);

    @Modifying
    @Query("UPDATE Lesson l SET l.course = null, l.task = null WHERE l.course = :course")
    void updateLessonsForDeletedCourse(@Param("course") Course course);

    @Modifying
    @Query("UPDATE Group g SET g.course = null WHERE g.course = :course")
    void updateGroupsForDeletedCourse(@Param("course") Course course);

    @Modifying
    @Query("DELETE FROM Course c WHERE c.id = :courseId")
    void deleteCourseById(@Param("courseId") Long courseId);
}
