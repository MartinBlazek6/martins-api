package com.martin.repositories.personrepositories;

import com.martin.entity.person.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education,Integer> {
}

