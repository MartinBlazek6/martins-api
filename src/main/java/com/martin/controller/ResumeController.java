package com.martin.controller;

import com.martin.entity.candidate.Resume;
import com.martin.entity.records.ResumeDTO;
import com.martin.repositories.ResumeRepository;
import com.martin.service.resume.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
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

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/saveMartinsResume")
    public ResponseEntity<String> saveMartinsResume(@RequestBody ResumeDTO resumeDTO) {
        try {
            resumeService.saveMartinsResume(resumeDTO);
            return new ResponseEntity<>("Resume saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("Error saving resume", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/forRecruiters/martinsResume")
    public ResponseEntity<?> getMartinsResume() {
        try {
            Resume resumes = resumeRepository.findByMartinsCvIsTrue();
            return new ResponseEntity<>(resumes, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Resume not found", HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/forRecruiters/deleteById/{resumeId}")
    public ResponseEntity<String> deleteById(@PathVariable Long resumeId) {
        resumeService.deleteResume(resumeId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/allResumes")
    public ResponseEntity<List<Resume>> getAllResumes() {
        List<Resume> resumes = resumeRepository.findAll();
        return new ResponseEntity<>(resumes, HttpStatus.OK);
    }

}
