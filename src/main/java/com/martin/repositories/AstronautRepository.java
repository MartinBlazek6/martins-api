package com.martin.repositories;

import com.martin.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AstronautRepository extends JpaRepository<Employment, Integer> {
    Employment findByFirstNameAndLastName(String name, String lastName);
}
