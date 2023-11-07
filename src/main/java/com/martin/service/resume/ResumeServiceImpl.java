package com.martin.service.resume;

import com.martin.entity.candidate.*;
import com.martin.entity.records.ResumeDTO;
import com.martin.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
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

    @Override
    public void saveMartinsResume(ResumeDTO resumeDTO) {
        Resume resume = resumeDTO.getResume();
        resume.setMartinsCv(true);
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


    @Override
    public void deleteResume(Long resumeId) {
       Resume resume = resumeRepository.findById(resumeId).orElseThrow();
        PersonalDetails personalDetails = resume.getPersonalDetails();
        personalDetailsRepository.delete(personalDetails);

        deleteRelatedEntities(resume, resume.getEmployments(), employmentRepository);
        deleteRelatedEntities(resume, resume.getEducations(), educationRepository);
        deleteRelatedEntities(resume, resume.getHardSkills(), hardSkillsRepository);
        deleteRelatedEntities(resume, resume.getSoftSkills(), softSkillsRepository);
    }


    private  <T, ID> void saveRelatedEntities(Resume resume, List<T> entities, JpaRepository<T, ID> repository) {
        if (entities != null){
            log.info("saving::" + entities.stream().findFirst().orElseThrow().getClass().getSimpleName());
            for (T entity : entities) {
                switch (entity.getClass().getSimpleName()) {
                    case "Employment" -> ((Employment) entity).setResume(resume);
                    case "Education" -> ((Education) entity).setResume(resume);
                    case "HardSkills" -> ((HardSkills) entity).setResume(resume);
                    case "SoftSkills" -> ((SoftSkills) entity).setResume(resume);

                    default -> log.error("Something went wrong!");
                }
                repository.save(entity);
            }
        }
    }


    private  <T, ID> void deleteRelatedEntities(Resume resume, List<T> entities, JpaRepository<T, ID> repository) {
        if (entities != null){
            log.info("deleting::" + entities.stream().findFirst().orElseThrow().getClass().getSimpleName());
//            for (T entity : entities) {
//                switch (entity.getClass().getSimpleName()) {
//                    case "Employment" -> ((Employment) entity).setResume(resume);
//                    case "Education" -> ((Education) entity).setResume(resume);
//                    case "HardSkills" -> ((HardSkills) entity).setResume(resume);
//                    case "SoftSkills" -> ((SoftSkills) entity).setResume(resume);
//
//                    default -> log.error("Something went wrong!");
//                }
//                repository.deleteAll(entities);
//            }
            repository.deleteAll(entities);
            resumeRepository.delete(resume);
        }
    }
}
