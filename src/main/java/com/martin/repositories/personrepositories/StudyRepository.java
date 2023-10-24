package com.martin.repositories.personrepositories;

import com.martin.entity.person.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<Study,Integer> {
}
