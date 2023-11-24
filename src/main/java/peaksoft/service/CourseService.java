package peaksoft.service;

import peaksoft.entity.Course;

import java.util.List;

public interface CourseService {

    void saveCourse(Long companyId, Course course);

    void deleteCourse(Long id);
    void updateCourse(Long id, Course newCourse);
    Course getCourseById(Long id);
    List<Course> getAll();
    List<Course> getAllCourseByCompanyId(Long companyId);
    void saveCourse(Course course);

}
