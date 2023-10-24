package com.martin.entity.person;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Employment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String project;
    private String technologies;
    private String duration;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

}
