package peaksoft.service;

import peaksoft.entity.Student;

import java.util.List;

public interface StudentService {

    void saveStudent(Long companyId, Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudent();
    List<Student> getAllStudentByCompanyId(Long id);
    void deleteStudent(Long id);
    void updateStudent(Long id, Student newStudent);
    void saveStudent( Student student);

}
