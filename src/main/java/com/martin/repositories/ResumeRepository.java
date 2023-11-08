package com.martin.repositories;

import com.martin.entity.candidate.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Resume findByMartinsCvIsTrue();
}
