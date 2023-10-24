package com.martin.entity.person;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Candidate candidate;

    // PersonalInfo fields
    private String address;
    private String phoneNumber;
}
