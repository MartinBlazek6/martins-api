package com.martin.entity.person;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Experiences {
    @Id
    private Integer id;
}
