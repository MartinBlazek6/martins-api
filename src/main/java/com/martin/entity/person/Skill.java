package com.martin.entity.person;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.*;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
    private String skillName;
    private String level;
}
