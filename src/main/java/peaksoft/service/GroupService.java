package peaksoft.service;

import peaksoft.entity.Group;

import java.util.List;

public interface GroupService {

    void saveGroup(Long companyId, Group group, List<Long> courseId);
    List<Group> getAllGroup();
    List<Group> getGroupByCourseId(Long courseId);
    List<Group> getGroupByCompanyId(Long companyId);
    Group getGroupById(Long id);
    void deleteGroup(Long id);
    void updateGroup(Long id, Group newGroup, List<Long> courseId);
    void assignStudentToGroup(Long groupId, List<Long> studentId);


}