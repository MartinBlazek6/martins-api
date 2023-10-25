package com.martin.service;

import com.martin.entity.person.*;
import com.martin.repositories.personrepositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final ResumeRepository resumeRepository;
    private final SkillsRepository skillsRepository;
    private final EmploymentRepository employmentRepository;
    private final EducationRepository educationRepository;
    private final PersonalInfoRepository personalInfoRepository;


    public List<Candidate> allCandidates(){
        return candidateRepository.findAll();
    }


    public List<Skill> allCandidatesSkills(){
        return candidateRepository.findAll().stream().findFirst().orElseThrow().getSkills();
    }


//    @Transactional  // Enables transaction management
    public Candidate createCandidateWithDetails(Candidate candidate) {
        Resume resume = candidate.getResume();
        PersonalInfo personalInfo = candidate.getPersonalInfo();
        List<Skill> skills = candidate.getSkills();
        List<Employment> experiences = candidate.getEmployments();
        List<Education> studies = candidate.getStudies();

//        resume.setCandidate(candidate);
        resumeRepository.save(resume);
//        personalInfo.setCandidate(candidate);
        personalInfoRepository.save(personalInfo);
//        skills.forEach(x->x.setCandidate(candidate));
        skillsRepository.saveAll(skills);
//        experiences.forEach(x->x.setCandidate(candidate));
        employmentRepository.saveAll(experiences);
//        studies.forEach(x->x.setCandidate(candidate));
        educationRepository.saveAll(studies);

        candidate.setResume(resume);
        candidate.setPersonalInfo(personalInfo);
        candidate.setSkills(skills);
        candidate.setEmployments(experiences);
        candidate.setStudies(studies);

        return candidateRepository.save(candidate);
    }
}
