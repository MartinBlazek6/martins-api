package com.martin.controller;

import com.martin.entity.Employment;
import com.martin.entity.PersonalDetails;
import com.martin.entity.Resume;
import com.martin.entity.records.ResumeDTO;
import com.martin.repositories.EmploymentRepository;
import com.martin.repositories.PersonalDetailsRepository;
import com.martin.repositories.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ResumeController {
    @Autowired
    private EmploymentRepository employmentRepository;
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;

    @PostMapping("/saveAstronautsAndCraft")
    public ResponseEntity<String> saveAstronautsAndCraft(@RequestBody ResumeDTO resumeDTO) {
        try {
            // Save the Craft to the database
            Resume resume = resumeDTO.getResume();
            PersonalDetails personalDetails = resumeDTO.getPersonalDetails();
            resumeRepository.save(resume);

            personalDetails.setResume(resume);
            personalDetailsRepository.save(personalDetails);
            // Save the Astronauts to the database
            List<Employment> employments = resumeDTO.getEmployments();
            for (Employment employment : employments) {
                employment.setResume(resume);
                employmentRepository.save(employment);
            }

            return new ResponseEntity<>("Astronauts and Craft saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("Error saving Astronauts and Craft", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
