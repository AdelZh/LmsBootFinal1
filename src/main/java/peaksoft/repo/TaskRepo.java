package peaksoft.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Task;


@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {



}
