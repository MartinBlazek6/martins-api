package com.martin.controller;

import com.martin.entity.Employment;
import com.martin.entity.PersonalDetails;
import com.martin.entity.Resume;
import com.martin.entity.ResumeMapper;
import com.martin.entity.records.ResumeDTO;
import com.martin.repositories.EmploymentRepository;
import com.martin.repositories.PersonalDetailsRepository;
import com.martin.repositories.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class ResumeController {
    @Autowired
    private EmploymentRepository employmentRepository;
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;

    @PostMapping("/saveResume")
    public ResponseEntity<String> saveResume(@RequestBody ResumeDTO resumeDTO) {
        try {
            Resume resume = resumeDTO.getResume();
            PersonalDetails personalDetails = resumeDTO.getPersonalDetails();

            // Save the Resume entity
            Resume savedResume = resumeRepository.save(resume);

            personalDetails.setResume(savedResume);

            // Save the PersonalDetails entity
            PersonalDetails savedPd = personalDetailsRepository.save(personalDetails);

            List<Employment> employments = resumeDTO.getEmployments();
            for (Employment employment : employments) {
                employment.setResume(savedResume);
                // Save each Employment entity
                employmentRepository.save(employment);
            }

            savedResume.setPersonalDetails(savedPd);
            // Update the Resume entity with the PersonalDetails reference
            resumeRepository.save(savedResume);

            return new ResponseEntity<>("Resume saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("Error saving resume", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/allResumes")
    public ResponseEntity<List<Resume>> getAllResumes() {
        List<Resume> resumes = resumeRepository.findAll();
        return new ResponseEntity<>(resumes, HttpStatus.OK);
    }


    @GetMapping("/retrieve/{resumeId}")
    public ResponseEntity<String> retrieveAstronautsAndCraft(@PathVariable Long resumeId) {
        try {
            // Retrieve the Resume entity
            Optional<Resume> resumeOptional = resumeRepository.findById(resumeId);

            if (resumeOptional.isPresent()) {
                // Map the Resume entity to a ResumeDTO
                ResumeDTO resumeDTO = ResumeMapper.mapToDTO(resumeOptional.get());
                return new ResponseEntity<>(resumeDTO.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Resume not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("Error retrieving Astronauts and Craft", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
