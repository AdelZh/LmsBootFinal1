package peaksoft.service.impl;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repo.CompanyRepo;

import peaksoft.repo.CourseRepo;
import peaksoft.repo.InstructorRepo;
import peaksoft.service.InstructorService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepo instructorRepo;
    private final CompanyRepo companyRepo;
    private final CourseRepo courseARepo;


    @Override
    public void saveInstructor(Long companyId, Instructor instructor) {
        Company company = companyRepo.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + companyId + " not found"));

        instructor.setCompany(company);
        instructorRepo.save(instructor);

    }

    @Override
    public void saveInstructor1(Instructor instructor) {
        instructorRepo.save(instructor);
    }

    @Override
    public List<Instructor> getAllInstructor() {
        return instructorRepo.findAll();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Instructor> getAllInstructorByCompany(Long companyId) {
        Company company = companyRepo.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + companyId + " not found"));

        return new ArrayList<>(company.getInstructors());
    }

    @Override
    public void deleteInstructor(Long id) {
        instructorRepo.deleteById(id);
    }

    @Override
    public void updateInstructor(Long id, Instructor newInstructor) {
        Instructor instructor = instructorRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        instructor.setFirstName(newInstructor.getFirstName());
        instructor.setLastName(newInstructor.getLastName());
        instructor.setPhoneNumber(newInstructor.getPhoneNumber());
        instructor.setSpecialization(newInstructor.getSpecialization());
        instructorRepo.save(instructor);
    }

    @Override
    public void bookCourse(Long courseId, List<Long> instructorIds) {
        Course course = courseARepo.findById(courseId).orElseThrow(EntityNotFoundException::new);
        if (course != null) {
            for (Long instructorId : instructorIds) {
                Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(EntityNotFoundException::new);

                if (instructor != null) {
                    instructor.setCourse(course);

                   instructorRepo.save(instructor);
                }
            }
        }
    }
}
