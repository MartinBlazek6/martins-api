package com.martin.repositories.personrepositories;

import com.martin.entity.person.Experiences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperiencesRepository extends JpaRepository<Experiences,Integer> {
}
