package com.martin.repositories.personrepositories;

import com.martin.entity.person.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment,Integer> {
}
