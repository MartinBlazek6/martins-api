package com.martin.entity.person;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.*;

@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private String degree;
    private String school;
    private String graduationDate;
}
