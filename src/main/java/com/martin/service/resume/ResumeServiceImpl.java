package com.martin.service.resume;

import com.martin.entity.Employment;
import com.martin.entity.PersonalDetails;
import com.martin.entity.Resume;
import com.martin.entity.records.ResumeDTO;
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

        savedResume.setPersonalDetails(savedPd);
        resumeRepository.save(savedResume);
    }
}
