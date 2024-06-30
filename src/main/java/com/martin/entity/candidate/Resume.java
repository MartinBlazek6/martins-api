package com.martin.entity.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.List;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Profession cannot be blank")
    private String profession;

    @NotBlank(message = "GDPR cannot be blank")
    private String GDPR;

    @NotNull(message = "Source code URL cannot be null")
    private URL sourceCodeOfThisProject;

    @JsonIgnore
    private boolean martinsCv;

    @JsonManagedReference
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Employment> employments;

    @JsonManagedReference
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Education> educations;

    @JsonManagedReference
    @OneToOne(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private PersonalDetails personalDetails;

    @JsonManagedReference
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<HardSkills> hardSkills;

    @JsonManagedReference
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<SoftSkills> softSkills;
}