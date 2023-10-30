package com.martin.controller;

import com.martin.entity.Employment;
import com.martin.entity.PersonalDetails;
import com.martin.entity.Resume;
import com.martin.entity.records.ResumeDTO;
import com.martin.repositories.EmploymentRepository;
import com.martin.repositories.PersonalDetailsRepository;
import com.martin.repositories.ResumeRepository;
import com.martin.service.resume.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeRepository resumeRepository;

    private final ResumeService resumeService;

    @PostMapping("/saveResume")
    public ResponseEntity<String> saveResume(@RequestBody ResumeDTO resumeDTO) {
        try {
            resumeService.saveResume(resumeDTO);
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

}
