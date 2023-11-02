package com.martin.controller;

import com.martin.entity.candidate.Resume;
import com.martin.entity.records.ResumeDTO;
import com.martin.repositories.ResumeRepository;
import com.martin.service.resume.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/forRecruiters/martinsResume")
    public ResponseEntity<Resume> getMartinsResume() {
        Resume resumes = resumeRepository.findAll().stream().findFirst().orElseThrow();
        return new ResponseEntity<>(resumes, HttpStatus.OK);
    }


    @GetMapping("/allResumes")
    public ResponseEntity<List<Resume>> getAllResumes() {
        List<Resume> resumes = resumeRepository.findAll();
        return new ResponseEntity<>(resumes, HttpStatus.OK);
    }

}
