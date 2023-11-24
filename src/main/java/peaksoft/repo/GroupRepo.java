package peaksoft.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Group;

import java.time.LocalDate;


@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {

    @Modifying
    @Query("UPDATE Group g SET g.groupName = :groupName, g.image = :image, g.dateOfStart = :dateOfStart WHERE g.id = :groupId")
    void updateGroup(@Param("groupId") Long groupId, @Param("groupName") String groupName, @Param("image") String image, @Param("dateOfStart") LocalDate dateOfStart);

}
