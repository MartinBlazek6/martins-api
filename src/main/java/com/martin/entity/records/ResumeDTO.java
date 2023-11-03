package com.martin.entity.records;

import com.martin.entity.candidate.*;
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
    private List<HardSkills> hardSkills;
    private List<SoftSkills> softSkills;
}
