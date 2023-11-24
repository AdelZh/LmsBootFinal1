package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Instructor;

import peaksoft.service.InstructorService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorApi {

    private final InstructorService instructorService;



    @GetMapping
    public String getAllInstructor(Model model) {
        model.addAttribute("allInstructor", instructorService.getAllInstructor());
        return "myInstructor/getAll";
    }

    @GetMapping("/update/{instructorId}")
    public String getById(@PathVariable Long instructorId, Model model) {
        model.addAttribute("instructor", instructorService.getInstructorById(instructorId));
        return "myInstructor/update";
    }

    @GetMapping("/byCompanyId/{companyId}")
    public String getAllInstructorByCompanyId(Model model, @PathVariable Long companyId) {
        model.addAttribute("instructorByCompany", instructorService.getAllInstructorByCompany(companyId));
        return "myInstructor/getAllInstructorByCompanyId";
    }

    @GetMapping("/create/{companyId}")
    public String createInstructorByCompany(@PathVariable Long companyId, Model model) {
        model.addAttribute("companyId", companyId);
        model.addAttribute("newInstructor", new Instructor());
        return "myInstructor/createInstructor";
    }


    @PostMapping("/save/{companyId}")
    public String saveInstructorByCompany(@PathVariable Long companyId, @ModelAttribute Instructor instructor) {
        instructorService.saveInstructor(companyId, instructor);
        return "redirect:/instructor";
    }


    @PostMapping("/merge/{instructorId}")
    public String updates(@ModelAttribute Instructor instructor, @PathVariable Long instructorId) {
        instructorService.updateInstructor(instructorId, instructor);
        return "redirect:/instructor";
    }


    @GetMapping("/delete/{instructorId}")
    public String deleteCourse(@PathVariable Long instructorId) {
        instructorService.deleteInstructor(instructorId);
        return "redirect:/instructor";
    }


}















