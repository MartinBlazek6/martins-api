package com.martin.entity.candidate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class Employment {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Duration cannot be blank")
    private String duration;

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @NotBlank(message = "Project name cannot be blank")
    private String projectName;

    @NotBlank(message = "Technologies cannot be blank")
    private String technologies;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    @JsonBackReference
    private Resume resume;
}
