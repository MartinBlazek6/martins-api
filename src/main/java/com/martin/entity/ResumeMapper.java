package com.martin.entity;

import com.martin.entity.records.ResumeDTO;
import com.martin.repositories.EmploymentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResumeMapper {

    private final PersonalDetails personalDetails;
    private final EmploymentRepository employmentRepository;

    public static ResumeDTO mapToDTO(Resume resume) {
        ResumeDTO resumeDTO = new ResumeDTO();
        resumeDTO.setResume(resume);
        resumeDTO.setPersonalDetails(resume.getPersonalDetails());
        resumeDTO.setEmployments(resume.getEmployments());
        return resumeDTO;
    }
}
