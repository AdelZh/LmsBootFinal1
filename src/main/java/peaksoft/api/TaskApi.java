package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Task;
import peaksoft.service.TaskService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskApi {


    private final TaskService taskService;

    @GetMapping
    public String getAllTask(Model model) {
        model.addAttribute("allTask", taskService.getAllTask());
        return "myTask/getAll";
    }

    @GetMapping("/{lessonId}")
    public String getAllTaskByLesson(Model model, @PathVariable Long lessonId) {
        model.addAttribute("taskByLesson", taskService.getTaskByLessonId(lessonId));
        return "myTask/getAllTaskByLesson";
    }

    @GetMapping("/create/{lessonId}")
    public String createTaskByLesson(@PathVariable Long lessonId, Model model) {
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("newTask", new Task());
        return "myTask/createTask";
    }


    @PostMapping("/save/{lessonId}")
    public String saveTaskByLesson(@PathVariable Long lessonId, @ModelAttribute Task task) {
        taskService.saveTask(lessonId, task);
        return "redirect:/task";
    }


    @GetMapping("/update/{taskId}")
    public String getById(@PathVariable Long taskId, Model model) {
        model.addAttribute("task", taskService.getTaskById(taskId));
        return "myTask/update-task";
    }

    @PostMapping("/merge/{taskId}")
    public String updates(@ModelAttribute Task task, @PathVariable Long taskId) {
        taskService.updateTask(taskId, task);
        return "redirect:/task";
    }


    @GetMapping("/delete/{taskId}")
    public String deleteLesson(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/task";
    }

}


