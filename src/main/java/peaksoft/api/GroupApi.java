package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.*;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupApi {

    private final GroupService groupService;
    private final StudentService studentService;
    private final CourseService courseService;


    @GetMapping
    public String getAllGroup(Model model) {
        model.addAttribute("allGroup", groupService.getAllGroup());
        return "myGroup/getAll";
    }


    @GetMapping("/{getById}/get")
    public String groupPage(@PathVariable("getById") Long groupID, Model model) {
        model.addAttribute("getGroupById", groupService.getGroupById(groupID));
        return "myGroup/getProfile";
    }


    @GetMapping("/groupByCourse/{courseId}")
    public String getAllGroupByCourseId(Model model, @PathVariable Long courseId) {
        model.addAttribute("GroupByCourse", groupService.getGroupByCourseId(courseId));
        return "myGroup/getAllGroupByCourseId";
    }


    @GetMapping("/byCompanyId/{companyId}")
    public String getAllGroupByCompanyId(Model model, @PathVariable Long companyId) {
        model.addAttribute("GroupByCompany", groupService.getGroupByCompanyId(companyId));
        return "myGroup/getAllGroupByCompanyId";
    }

    @GetMapping("/create/{companyId}")
    public String createGroupByCompany(@PathVariable Long companyId, Model model) {
        model.addAttribute("companyId", companyId);
        model.addAttribute("newGroup", new Group());
        model.addAttribute("allCourses", courseService.getAll());
        return "myGroup/createGroup";
    }

    @PostMapping("/save/{companyId}")
    public String saveGroupByCourse(@PathVariable Long companyId, @ModelAttribute Group group,  @RequestParam List<Long> courseId) {
        groupService.saveGroup(companyId, group, courseId);
        return "redirect:/group";
    }




    @GetMapping("/update/{groupId}")
    public String getById(@PathVariable Long groupId, Model model) {
        model.addAttribute("group", groupService.getGroupById(groupId));
        model.addAttribute("allCourses", courseService.getAll());
        return "myGroup/update-group";
    }

    @PostMapping("/merge/{groupId}")
    public String updates(@ModelAttribute Group group, @PathVariable Long groupId,  @RequestParam List<Long> courseId) {
        groupService.updateGroup(groupId, group, courseId);
        return "redirect:/group";
    }



    @GetMapping("/delete/{groupId}")
    public String deleteCourse(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
        return "redirect:/group";
    }

    @GetMapping("/{getById}/get/booking")
    public String showAndBookGroup(@PathVariable("getById") Long groupId, Model model) {
        Group group = groupService.getGroupById(groupId);
        List<Student> availableStudent = studentService.getAllStudent();

        model.addAttribute("getGroupById", group);
        model.addAttribute("students", availableStudent);
        return "myGroup/student-assign-group";
    }


    @PostMapping("/{getById}/get/booking")
    public String bookGroup(@RequestParam List<Long> groupId, @PathVariable Long getById) {
        Student student = studentService.getStudentById(getById);
        groupService.assignStudentToGroup(student.getId(), groupId);
        return "redirect:/company";
    }
}



































    /*@GetMapping("/groupByCourse/{courseId}")
    public String getAllGroupByCourseId(Model model, @PathVariable Long courseId) {
        model.addAttribute("GroupByCourse", groupService.getGroupByCourseId(courseId));
        return "myGroup/getAllGroupByCourseId";
    }

     @GetMapping("/create/{courseId}")
    public String createGroupByCompany(@PathVariable Long courseId, Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("newGroup", new Group());
        return "myGroup/createGroup";
    }



    @PostMapping("/save/{courseId}")
    public String saveGroupByCourse(@PathVariable Long courseId, @ModelAttribute Group group) {
        groupService.saveGroup(courseId, group);
        return "redirect:/group";
    }
     */

