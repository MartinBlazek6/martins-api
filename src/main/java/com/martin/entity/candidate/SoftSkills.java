package com.martin.entity.candidate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class SoftSkills {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String skill;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    @JsonBackReference
    private Resume resume;
}