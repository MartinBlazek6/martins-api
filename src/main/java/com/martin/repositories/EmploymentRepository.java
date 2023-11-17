package com.martin.repositories;

import com.martin.entity.candidate.Employment;
import com.martin.entity.candidate.Resume;
import com.martin.entity.candidate.SoftSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Integer> {
    List<Employment> findAllByResume(Resume resume);

}
