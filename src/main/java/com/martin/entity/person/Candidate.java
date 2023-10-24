package com.martin.entity.person;

import javax.persistence.*;
import java.util.List;


@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private PersonalInfo personalInfo;

    @OneToMany(mappedBy = "candidate")
    private List<Employment> employments;

    @OneToMany(mappedBy = "candidate")
    private List<Education> studies;

    @OneToMany(mappedBy = "candidate")
    private List<Skill> skills;

    @OneToMany(mappedBy = "candidate")
    private List<Language> languages;

    @OneToOne(mappedBy = "candidate")
    private Resume resume;

    private String firstName;
    private String lastName;
    private String email;
}
