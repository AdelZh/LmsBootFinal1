package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.repo.CompanyRepo;
import peaksoft.repo.CourseRepo;
import peaksoft.repo.GroupRepo;
import peaksoft.repo.StudentRepo;
import peaksoft.service.GroupService;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepo groupRepo;
    private final CourseRepo courseARepo;
    private final StudentRepo studentRepo;
    private final CompanyRepo companyRepo;


    @Override
    public void saveGroup(Long companyId, Group group, List<Long> courseId) {
        Company company = companyRepo.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + companyId + " not found"));
        for(Long courseIds : courseId){
            Course course=courseARepo.findById(courseIds).orElseThrow(EntityNotFoundException::new);
            group.setCourse(course);
        }
        group.setCompany(company);
        groupRepo.save(group);

    }

    @Override
    public List<Group> getGroupByCompanyId(Long companyId) {
        Company company = companyRepo.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + companyId + " not found"));
        return new ArrayList<>(company.getGroups());
    }

    @Override
    public List<Group> getAllGroup() {
        return groupRepo.findAll();
    }

    @Override
    public List<Group> getGroupByCourseId(Long courseId) {
        Course course = courseARepo.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));
        return new ArrayList<>(course.getGroup());
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepo.deleteById(id);
    }

    @Override
    public void updateGroup(Long id, Group newGroup, List<Long> courseId ) {
        Group group = groupRepo.findById(id).orElse(null);

        if (group != null) {
            group.setGroupName(newGroup.getGroupName());
            group.setImage(newGroup.getImage());
            group.setDateOfStart(newGroup.getDateOfStart());


            for (Long courseId1 : courseId) {
                Course course = courseARepo.findById(courseId1).orElseThrow(EntityNotFoundException::new);
                group.setCourse(course);

                groupRepo.save(group);
            }
        }

    }

    @Override
    public void assignStudentToGroup(Long groupId, List<Long> studentId) {
        Group group = groupRepo.findById(groupId).orElseThrow(EntityNotFoundException::new);

        if (group != null) {
            for (Long studentIds : studentId) {
                Student student = studentRepo.findById(studentIds).orElseThrow(EntityNotFoundException::new);

                if (student != null) {
                    student.setGroup(group);

                    studentRepo.save(student);
                }
            }
        }
    }

}