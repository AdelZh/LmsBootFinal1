package peaksoft.service.impl;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.repo.CompanyRepo;

import peaksoft.repo.CourseRepo;
import peaksoft.service.CourseService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {


    private final CourseRepo courseARepo;
    private final CompanyRepo companyRepo;

    @Override
    public void saveCourse(Long companyId, Course course) {
        Company company = companyRepo.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + companyId + " not found"));

        course.setCompany(company);
        courseARepo.save(course);
    }


    @Override
    public void saveCourse(Course course) {
        courseARepo.save(course);
    }


    @Override
    public void deleteCourse(Long id) {
        Course course = courseARepo.findById(id).orElse(null);

        if (course != null) {
            courseARepo.updateGroupsForDeletedCourse(course);
            courseARepo.updateInstructorsForDeletedCourse(course);
            courseARepo.updateLessonsForDeletedCourse(course);
            courseARepo.deleteCourseById(id);
        }
    }

    @Override
    public void updateCourse(Long id, Course newCourse) {
        Course course = courseARepo.findById(id).orElseThrow(EntityNotFoundException::new);
        course.setCourseName(newCourse.getCourseName());
        course.setDescription(newCourse.getDescription());
        course.setDuration(newCourse.getDuration());
        courseARepo.save(course);

    }

    @Override
    public Course getCourseById(Long id) {
        return courseARepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Course> getAll() {
        return courseARepo.findAll();
    }

    @Override
    public List<Course> getAllCourseByCompanyId(Long companyId) {
        Company company = companyRepo.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + companyId + " not found"));
        return new ArrayList<>(company.getCourse());
    }

}
