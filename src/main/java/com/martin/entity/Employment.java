package com.martin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;


    @Override
    public String toString() {
        return "Astronaut{" +
                "name='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}