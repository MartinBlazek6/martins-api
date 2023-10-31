package com.martin.entity.records;

import com.martin.entity.candidate.Education;
import com.martin.entity.candidate.Employment;
import com.martin.entity.candidate.PersonalDetails;
import com.martin.entity.candidate.Resume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeDTO {
    private Resume resume;
    private PersonalDetails personalDetails;
    private List<Employment> employments;
    private List<Education> educations;
}
