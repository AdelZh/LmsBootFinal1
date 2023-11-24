package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Student;
import peaksoft.service.StudentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentApi {

    private final StudentService studentService;

    @GetMapping
    public String getAllStudent(Model model) {
        model.addAttribute("allStudent", studentService.getAllStudent());
        return "myStudent/getAll";
    }

    @GetMapping("/byCompanyId/{companyId}")
    public String getAllStudentByCompanyId(Model model, @PathVariable Long companyId) {
        model.addAttribute("studentByCompany", studentService.getAllStudentByCompanyId(companyId));
        return "myStudent/getAllStudentByCompanyId";
    }

    @GetMapping("/create/{companyId}")
    public String createCourseByCompany(@PathVariable Long companyId, Model model) {
        model.addAttribute("companyId", companyId);
        model.addAttribute("newStudent", new Student());
        return "myStudent/createStudent";
    }


    @PostMapping("/save/{companyId}")
    public String saveStudentByCompany(@PathVariable Long companyId, @ModelAttribute Student student) {
        studentService.saveStudent(companyId, student);
        return "redirect:/student";
    }


    @GetMapping("/update/{studentId}")
    public String getById(@PathVariable Long studentId, Model model) {
        model.addAttribute("student", studentService.getStudentById(studentId));
        return "myStudent/update-student";
    }

    @PostMapping("/merge/{studentId}")
    public String updates(@ModelAttribute Student student, @PathVariable Long studentId) {
        studentService.updateStudent(studentId, student);
        return "redirect:/student";
    }


    @GetMapping("/delete/{studentId}")
    public String deleteCourse(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/student";
    }

}
