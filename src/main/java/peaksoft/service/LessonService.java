package peaksoft.service;

import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonService {

    void saveLesson(Long courseId, Lesson lesson);
    Lesson getLessonById(Long id);
    List<Lesson> getAllLesson();
    List<Lesson> getLessonByCourseId(Long courseId);
    void deleteLesson(Long id);
    void updateLesson(Long id, Lesson newLesson);

}
