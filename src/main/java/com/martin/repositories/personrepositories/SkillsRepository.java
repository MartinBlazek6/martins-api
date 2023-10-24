package com.martin.repositories.personrepositories;

import com.martin.entity.person.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends JpaRepository<Skill,Integer> {
}
