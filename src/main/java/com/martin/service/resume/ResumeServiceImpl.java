package com.martin.service.resume;

import com.martin.entity.candidate.*;
import com.martin.entity.dataTransferObjects.ResumeDTO;
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


    /**
     * Save a new resume and its associated details, including personal information, employments, educations,
     * hard skills, and soft skills.
     *
     * @param resumeDTO The DTO containing the resume and associated details.
     */
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

    /**
     * Save Martin's resume by marking it as such and then invoking the general saveResume method.
     *
     * @param resumeDTO The DTO containing Martin's resume and associated details.
     */
    @Override
    public void saveMartinsResume(ResumeDTO resumeDTO) {
        resumeDTO.getResume().setMartinsCv(true);
        saveResume(resumeDTO);
    }

    /**
     * Delete a resume and its associated details, including personal information, employments, educations,
     * hard skills, and soft skills.
     *
     * @param resumeId The ID of the resume to be deleted.
     */
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

    @Override
    public void updateRelatedEntities(ResumeDTO resumeDTO, Long id) {
        Resume resume = resumeRepository.findById(id).orElseThrow();

        if (resumeDTO.getResume() != null) {
            resumeRepository.delete(resume);
            Resume updatedResume = resumeDTO.getResume();
            updatedResume.setId(resume.getId());
            resumeRepository.save(updatedResume);
        }

        if (resumeDTO.getPersonalDetails() != null) {
            PersonalDetails personalDetails = resume.getPersonalDetails();
            PersonalDetails updatedPersonalDetails = resumeDTO.getPersonalDetails();

            personalDetails.setName(updatedPersonalDetails.getName() != null ? resumeDTO.getPersonalDetails().getName() : personalDetails.getName());
            personalDetails.setEmail(updatedPersonalDetails.getEmail() != null ? resumeDTO.getPersonalDetails().getEmail() : personalDetails.getEmail());
            personalDetails.setLocation(updatedPersonalDetails.getLocation() != null ? resumeDTO.getPersonalDetails().getLocation() : personalDetails.getLocation());
            personalDetails.setPhoneNumber(updatedPersonalDetails.getPhoneNumber() != null ? resumeDTO.getPersonalDetails().getPhoneNumber() : personalDetails.getPhoneNumber());

            personalDetailsRepository.save(personalDetails);
        }


        if (resumeDTO.getEmployments() != null) {
            employmentRepository.deleteAll(employmentRepository.findAllByResume(resume));
            saveRelatedEntities(resume, resumeDTO.getEmployments(), employmentRepository);
        }

        if (resumeDTO.getEducations() != null) {
            educationRepository.deleteAll(educationRepository.findAllByResume(resume));
            saveRelatedEntities(resume, resumeDTO.getEducations(), educationRepository);
        }

        if (resumeDTO.getHardSkills() != null) {
            hardSkillsRepository.deleteAll(hardSkillsRepository.findAllByResume(resume));
            saveRelatedEntities(resume, resumeDTO.getHardSkills(), hardSkillsRepository);
        }

        if (resumeDTO.getSoftSkills() != null) {
            softSkillsRepository.deleteAll(softSkillsRepository.findAllByResume(resume));
            saveRelatedEntities(resume, resumeDTO.getSoftSkills(), softSkillsRepository);
        }

    }

    /**
     * Save a list of related entities associated with a resume.
     *
     * @param resume     The resume to which the entities are related.
     * @param entities   The list of entities to be saved.
     * @param repository The repository for the type of entities being saved.
     * @param <T>        The type of entities.
     * @param <ID>       The type of entity ID.
     */
    private <T, ID> void saveRelatedEntities(Resume resume, List<T> entities, JpaRepository<T, ID> repository) {
        if (entities != null) {
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

    /**
     * Delete a list of related entities associated with a resume.
     *
     * @param resume     The resume from which the entities are related.
     * @param entities   The list of entities to be deleted.
     * @param repository The repository for the type of entities being deleted.
     * @param <T>        The type of entities.
     * @param <ID>       The type of entity ID.
     */
    private <T, ID> void deleteRelatedEntities(Resume resume, List<T> entities, JpaRepository<T, ID> repository) {
        if (entities != null) {
            log.info("deleting::" + entities.stream().findFirst().orElseThrow().getClass().getSimpleName());
            repository.deleteAll(entities);
            resumeRepository.delete(resume);
        }
    }
}
