package com.martin.repositories;

import com.martin.entity.candidate.HardSkills;
import com.martin.entity.candidate.Resume;
import com.martin.entity.candidate.SoftSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HardSkillsRepository extends JpaRepository<HardSkills,Integer> {
    List<HardSkills> findAllByResume(Resume resume);

}
