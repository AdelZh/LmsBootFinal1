package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.repo.CourseRepo;
import peaksoft.repo.LessonRepo;
import peaksoft.service.LessonService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {


    private final LessonRepo lessonRepo;
    private final CourseRepo courseRepo;
    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
        Course course=courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("entity not found: "+courseId));
        lesson.setCourse(course);
        lessonRepo.save(lesson);

    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("entity not found: "+id));

    }

    @Override
    public List<Lesson> getAllLesson() {
        return lessonRepo.findAll();
    }

    @Override
    public List<Lesson> getLessonByCourseId(Long courseId) {
        Course course=courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("entity not found: "+courseId));
        if (course != null && course.getLesson()!= null){
           return new ArrayList<>(course.getLesson());
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepo.deleteById(id);
    }

    @Override
    public void updateLesson(Long id, Lesson newLesson) {
        Lesson lesson=lessonRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        lesson.setLessonName(newLesson.getLessonName());
        lesson.setTask(newLesson.getTask());
        lessonRepo.save(lesson);
    }
}