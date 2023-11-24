package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Lesson;
import peaksoft.service.LessonService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonApi {


    private final LessonService lessonService;


    @GetMapping
    public String getAllLesson(Model model) {
        model.addAttribute("allLesson", lessonService.getAllLesson());
        return "myLesson/getAll";
    }

    @GetMapping("/lessonByCourse/{courseId}")
    public String getAllLessonByCourseId(Model model, @PathVariable Long courseId) {
        model.addAttribute("LessonByCourse", lessonService.getLessonByCourseId(courseId));
        return "myLesson/getAllLessonByCourseId";
    }

    @GetMapping("/{getById}/get")
    public String lessonPage(@PathVariable("getById") Long lessonID, Model model) {
        model.addAttribute("getLessonById", lessonService.getLessonById(lessonID));
        return "myLesson/getProfile";
    }


    @GetMapping("/create/{courseId}")
    public String createLessonByGroup(@PathVariable Long courseId, Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("newLesson", new Lesson());
        return "myLesson/createLesson";
    }


    @PostMapping("/save/{courseId}")
    public String saveLessonByGroup(@PathVariable Long courseId, @ModelAttribute Lesson lesson) {
        lessonService.saveLesson(courseId,lesson );
        return "redirect:/lesson";
    }


    @GetMapping("/update/{lessonId}")
    public String getById(@PathVariable Long lessonId, Model model) {
        model.addAttribute("lesson", lessonService.getLessonById(lessonId));
        return "myLesson/update-lesson";
    }

    @PostMapping("/merge/{lessonId}")
    public String updates(@ModelAttribute Lesson lesson, @PathVariable Long lessonId) {
        lessonService.updateLesson(lessonId, lesson);
        return "redirect:/lesson";
    }


    @GetMapping("/delete/{lessonId}")
    public String deleteLesson(@PathVariable Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return "redirect:/lesson";
    }

}
