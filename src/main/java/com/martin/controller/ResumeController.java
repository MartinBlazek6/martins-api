package com.martin.controller;

import com.martin.entity.Employment;
import com.martin.entity.Resume;
import com.martin.entity.records.ResumeDTO;
import com.martin.repositories.EmploymentRepository;
import com.martin.repositories.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResumeController {
    @Autowired
    private EmploymentRepository employmentRepository;
    @Autowired
    private ResumeRepository resumeRepository;

    @PostMapping("/saveAstronautsAndCraft")
    public ResponseEntity<String> saveAstronautsAndCraft(@RequestBody ResumeDTO resumeDTO) {
        try {
            // Save the Craft to the database
            Resume resume = resumeDTO.getResume();
            resumeRepository.save(resume);

            // Save the Astronauts to the database
            List<Employment> employments = resumeDTO.getEmployments();
            for (Employment employment : employments) {
                employment.setResume(resume); // Set the Craft for each Astronaut
                employmentRepository.save(employment);
            }

            return new ResponseEntity<>("Astronauts and Craft saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving Astronauts and Craft", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
