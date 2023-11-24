package peaksoft.service.impl;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import peaksoft.entity.Company;
import peaksoft.entity.Student;
import peaksoft.repo.CompanyRepo;
import peaksoft.repo.StudentRepo;
import peaksoft.service.StudentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {


    private final StudentRepo studentRepo;
    private final CompanyRepo companyRepo;


    @Override
    public void saveStudent(Long companyId, Student student) {
        Company company = companyRepo.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Group with id " + companyId + " not found"));
        student.setCompany(company);
        studentRepo.save(student);

    }

    @Override
    public void saveStudent(Student student) {
        studentRepo.save(student);

    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    @Override
    public List<Student> getAllStudentByCompanyId(Long id) {
        Company company=companyRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        if(company!=null && company.getStudents()!=null){
            return new ArrayList<>(company.getStudents());
        }else {
            return Collections.emptyList();
        }

    }

    @Override
    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }

    @Override
    public void updateStudent(Long id, Student newStudent) {
        Student student=getStudentById(id);
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setEmail(newStudent.getEmail());
        student.setPhoneNumber(newStudent.getPhoneNumber());
        studentRepo.save(student);
    }


}
