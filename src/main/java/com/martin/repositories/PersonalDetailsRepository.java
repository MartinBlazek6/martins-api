package com.martin.repositories;

import com.martin.entity.candidate.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.martin.entity.candidate.PersonalDetails;

@Repository
public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails,Integer> {
    PersonalDetails findByResume(Resume resume);
}
