package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseApi {

    private final CourseService courseService;
    private final InstructorService instructorService;


    @GetMapping
    public String getAllCourse(Model model) {
        model.addAttribute("allCourse", courseService.getAll());
        return "myCourse/getAll";
    }

    @GetMapping("/byCompanyId/{companyId}")
    public String getAllCourseByCompanyId(Model model, @PathVariable Long companyId) {
        System.out.println("course ID: " + companyId);
        model.addAttribute("courseByCompany", courseService.getAllCourseByCompanyId(companyId));
        return "myCourse/getAllCourseByCompanyId";

    }

    @GetMapping("/create/{companyId}")
    public String createCourseByCompany(@PathVariable Long companyId, Model model) {
        model.addAttribute("companyId", companyId);
        model.addAttribute("newCourse", new Course());
        return "myCourse/createCourse";
    }


    @PostMapping("/save/{companyId}")
    public String saveCourseByCompany(@PathVariable Long companyId, @ModelAttribute Course course) {
        courseService.saveCourse(companyId, course);
        return "redirect:/course";
    }


    @GetMapping("/update/{courseId}")
    public String getById(@PathVariable Long courseId, Model model) {
        model.addAttribute("course", courseService.getCourseById(courseId));
        return "myCourse/update";
    }

    @PostMapping("/merge/{courseId}")
    public String updates(@ModelAttribute Course course, @PathVariable Long courseId) {
        courseService.updateCourse(courseId, course);
        return "redirect:/course";
    }


    @GetMapping("/delete/{courseId}")
    public String deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return "redirect:/course";
    }

    @GetMapping("/{getById}/get/booking")
    public String showAndBookCourse(@PathVariable("getById") Long courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        List<Instructor> availableInstructor = instructorService.getAllInstructor();

        model.addAttribute("getCourseById", course);
        model.addAttribute("instructors", availableInstructor);
        return "myCourse/instructor-assign-course";
    }


    @PostMapping("/{getById}/get/booking")
    public String bookCourse(@RequestParam List<Long> courseId, @PathVariable Long getById) {
        Instructor instructor = instructorService.getInstructorById(getById);
        instructorService.bookCourse(instructor.getId(), courseId);
        return "redirect:/company";
    }


}



















































































/*    @GetMapping("/{getById}/get/booking")
    public String showAndBookCourse(@PathVariable("getById") Long instructorId, Model model) {
        Course course=courseService.getCourseById(instructorId);
        List<Instructor> availableInstructor = instructorService.getAllInstructor();

        model.addAttribute("getInstructorById", course);
        model.addAttribute("courses", availableInstructor);
        return "myCourse/instructor-assign-course";
    }



    @PostMapping("/{getById}/get/booking")
    public String bookCourse(@RequestParam List<Long> courseId, @PathVariable Long getById) {
        Instructor instructor=instructorService.getInstructorById(getById);
        instructorService.bookCourse(instructor.getId(),courseId);
        return "redirect:/company";
    }
}

 */