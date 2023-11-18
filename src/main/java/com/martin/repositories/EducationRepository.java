package com.martin.repositories;

import com.martin.entity.candidate.Education;
import com.martin.entity.candidate.Employment;
import com.martin.entity.candidate.Resume;
import com.martin.entity.candidate.SoftSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {
    List<Education> findAllByResume(Resume resume);

}
