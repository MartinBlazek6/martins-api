package com.martin.repositories;

import com.martin.entity.candidate.SoftSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftSkillsRepository extends JpaRepository<SoftSkills,Integer> {
}
