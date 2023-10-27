package com.martin.repositories;

import com.martin.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CraftRepository extends JpaRepository<Resume, Long> {
    boolean existsByCraftName(String name);
    Resume findByCraftName(String name);

//    @Query("SELECT c FROM Resume c JOIN c.astronauts a WHERE a.firstName = :firstName AND a.lastName = :lastName")
//    Resume findCraftByAstronautNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
