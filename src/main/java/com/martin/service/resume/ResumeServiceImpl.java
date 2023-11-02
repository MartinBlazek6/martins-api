package com.martin.service.resume;

import com.martin.entity.candidate.Education;
import com.martin.entity.candidate.Employment;
import com.martin.entity.candidate.PersonalDetails;
import com.martin.entity.candidate.Resume;
import com.martin.entity.records.ResumeDTO;
import com.martin.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final PersonalDetailsRepository personalDetailsRepository;
    private final EmploymentRepository employmentRepository;
    private final EducationRepository educationRepository;
    private final HardSkillsRepository hardSkillsRepository;
    private final SoftSkillsRepository softSkillsRepository;

    public void saveResume(ResumeDTO resumeDTO) {
        Resume resume = resumeDTO.getResume();
        PersonalDetails personalDetails = resumeDTO.getPersonalDetails();

        Resume savedResume = resumeRepository.save(resume);
        personalDetails.setResume(savedResume);
        PersonalDetails savedPd = personalDetailsRepository.save(personalDetails);

        savedResume.setPersonalDetails(savedPd);
        resumeRepository.save(savedResume);

        saveRelatedEntities(savedResume, resumeDTO.getEmployments(), employmentRepository);
        saveRelatedEntities(savedResume, resumeDTO.getEducations(), educationRepository);
        saveRelatedEntities(savedResume, resumeDTO.getHardSkills(), hardSkillsRepository);
        saveRelatedEntities(savedResume, resumeDTO.getSoftSkills(), softSkillsRepository);
    }
    private  <T, ID> void saveRelatedEntities(Resume resume, List<T> entities, JpaRepository<T, ID> repository) {
        if (entities != null){
            log.info("saving::" + entities.stream().findFirst().orElseThrow().getClass().getSimpleName());
            for (T entity : entities) {
                switch (entity.getClass().getSimpleName()) {
                    case "Employment" -> ((Employment) entity).setResume(resume);
                    case "Education" -> ((Education) entity).setResume(resume);

                    default -> {
                    }
                }
                repository.save(entity);
            }
        }
    }
}
