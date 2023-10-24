package com.martin.entity.person;

import jdk.jfr.Enabled;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Candidate {

    @Id
    private Integer id;
}
