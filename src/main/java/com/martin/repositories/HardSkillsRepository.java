package com.martin.repositories;

import com.martin.entity.candidate.HardSkills;
import com.martin.entity.candidate.SoftSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardSkillsRepository extends JpaRepository<HardSkills,Integer> {
}
