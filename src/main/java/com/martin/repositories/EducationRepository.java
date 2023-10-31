package com.martin.repositories;

import com.martin.entity.candidate.Education;
import com.martin.entity.candidate.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {
}
