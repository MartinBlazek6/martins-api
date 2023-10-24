package com.martin.repositories.personrepositories;

import com.martin.entity.person.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo,Integer> {
}
