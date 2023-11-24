package peaksoft.service;

import peaksoft.entity.Lesson;
import peaksoft.entity.Task;

import java.util.List;

public interface TaskService {

    void saveTask(Long lessonId, Task task);
    void deleteTask(Long id);
    List<Lesson> getAllTask();
    List<Task> getTaskByLessonId(Long taskId);
    void updateTask(Long id, Task newTask);
    Task getTaskById(Long taskId);
}
