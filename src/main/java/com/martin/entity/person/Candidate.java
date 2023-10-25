package com.martin.entity.person;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private PersonalInfo personalInfo;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Employment> employments;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Education> studies;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Skill> skills;

    @OneToMany(mappedBy = "candidate")
    private List<Language> languages;

    @OneToOne(mappedBy = "candidate")
    private Resume resume;

    private String description;
    private String firstName;
    private String lastName;
    private String email;
}
