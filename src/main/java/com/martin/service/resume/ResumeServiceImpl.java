package com.martin.service.resume;

import com.martin.entity.candidate.Education;
import com.martin.entity.candidate.Employment;
import com.martin.entity.candidate.PersonalDetails;
import com.martin.entity.candidate.Resume;
import com.martin.entity.records.ResumeDTO;
import com.martin.repositories.EducationRepository;
import com.martin.repositories.EmploymentRepository;
import com.martin.repositories.PersonalDetailsRepository;
import com.martin.repositories.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeServiceImpl implements ResumeService{

    private final ResumeRepository resumeRepository;
    private final PersonalDetailsRepository personalDetailsRepository;
    private final EmploymentRepository employmentRepository;
    private final EducationRepository educationRepository;
    @Override
    public void saveResume(ResumeDTO resumeDTO){
        Resume resume = resumeDTO.getResume();
        PersonalDetails personalDetails = resumeDTO.getPersonalDetails();

        Resume savedResume = resumeRepository.save(resume);

        personalDetails.setResume(savedResume);

        PersonalDetails savedPd = personalDetailsRepository.save(personalDetails);

        List<Employment> employments = resumeDTO.getEmployments();
        for (Employment employment : employments) {
            employment.setResume(savedResume);
            employmentRepository.save(employment);
        }

        List<Education> educations = resumeDTO.getEducations();
        for (Education education : educations) {
            education.setResume(savedResume);
            educationRepository.save(education);
        }

        savedResume.setPersonalDetails(savedPd);
        resumeRepository.save(savedResume);
    }
}
