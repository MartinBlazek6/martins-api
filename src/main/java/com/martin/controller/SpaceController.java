package com.martin.controller;

import com.martin.entity.Employment;
import com.martin.entity.Resume;
import com.martin.entity.records.ResumeDTO;
import com.martin.repositories.AstronautRepository;
import com.martin.repositories.CraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpaceController {
    @Autowired
    private AstronautRepository astronautRepository; // Define an appropriate repository for Astronaut
    @Autowired
    private CraftRepository craftRepository; // Define an appropriate repository for Craft

    @PostMapping("/saveAstronautsAndCraft")
    public ResponseEntity<String> saveAstronautsAndCraft(@RequestBody ResumeDTO resumeDTO) {
        try {
            // Save the Craft to the database
            Resume resume = resumeDTO.getResume();
            craftRepository.save(resume);

            // Save the Astronauts to the database
            List<Employment> employments = resumeDTO.getEmployments();
            for (Employment employment : employments) {
                employment.setResume(resume); // Set the Craft for each Astronaut
                astronautRepository.save(employment);
            }

            return new ResponseEntity<>("Astronauts and Craft saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving Astronauts and Craft", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
