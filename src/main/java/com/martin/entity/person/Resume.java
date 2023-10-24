package com.martin.entity.person;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.*;

@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Resume title
    private String summary; // Brief summary or objective

    @OneToOne
    private Candidate candidate;
}
