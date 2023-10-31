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

//    @Override
//    public void saveResume(ResumeDTO resumeDTO) {
//        Resume resume = resumeDTO.getResume();
//        PersonalDetails personalDetails = resumeDTO.getPersonalDetails();
//
//        Resume savedResume = resumeRepository.save(resume);
//
//        personalDetails.setResume(savedResume);
//
//        PersonalDetails savedPd = personalDetailsRepository.save(personalDetails);
//
//        List<Employment> employments = resumeDTO.getEmployments();
//        for (Employment employment : employments) {
//            employment.setResume(savedResume);
//            employmentRepository.save(employment);
//        }
//
//        List<Education> educations = resumeDTO.getEducations();
//        for (Education education : educations) {
//            education.setResume(savedResume);
//            educationRepository.save(education);
//        }
//
////        mapAndSaveRelatedEntities(savedResume, resumeDTO, Collections.singletonList(educations));
////        mapAndSaveRelatedEntities(savedResume, resumeDTO, Collections.singletonList(employments));
//
//        savedResume.setPersonalDetails(savedPd);
//        resumeRepository.save(savedResume);
//    }

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
    }

    @Override
    public  <T, ID> void saveRelatedEntities(Resume resume, List<T> entities, JpaRepository<T, ID> repository) {
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
