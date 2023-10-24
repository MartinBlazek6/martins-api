package com.martin.repositories.personrepositories;

import com.martin.entity.person.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume,Integer> {
}
