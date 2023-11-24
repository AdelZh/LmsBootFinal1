package peaksoft.service.impl;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.repo.LessonRepo;
import peaksoft.repo.TaskRepo;
import peaksoft.service.TaskService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class TaskServiceImpl implements TaskService {

    private final LessonRepo lessonRepo;
    private final TaskRepo taskRepo;
    @Override
    public void saveTask(Long lessonId, Task task) {
        Lesson lesson=lessonRepo.findById(lessonId).orElseThrow(()-> new EntityNotFoundException("not found: "+lessonId));
        task.setLesson(lesson);
        taskRepo.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        lessonRepo.deleteById(id);
    }

    @Override
    public List<Lesson> getAllTask() {
        return lessonRepo.findAll();
    }

    @Override
    public List<Task> getTaskByLessonId(Long taskId) {
        Lesson lesson=lessonRepo.findById(taskId).orElseThrow(()-> new EntityNotFoundException("not found: "+taskId));
        if (lesson != null && lesson.getTask() != null){
            return new ArrayList<>(lesson.getTask());
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public void updateTask(Long id, Task newTask) {
        Task task=taskRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        task.setTaskText(newTask.getTaskText());
        task.setTaskName(newTask.getTaskName());
        task.setDeadline(newTask.getDeadline());
        taskRepo.save(task);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepo.findById(taskId).orElseThrow(() -> new EntityNotFoundException("not found: "+taskId));
    }
}