package com.martin.controller;

import com.martin.entity.candidate.Resume;
import com.martin.entity.dataTransferObjects.ResumeDTO;
import com.martin.repositories.ResumeRepository;
import com.martin.service.resume.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            resumeService.saveResume(resumeDTO);
            return new ResponseEntity<>("Resume saved successfully", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('Admin')")
    @PatchMapping("/updateResume")
    public ResponseEntity<String> updateResume(
            @RequestBody ResumeDTO resumeDTO,
            @RequestParam Long resumeId) {
            resumeService.updateRelatedEntities(resumeDTO,resumeId);
            return new ResponseEntity<>("Resume updated successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/saveMartinsResume")
    public ResponseEntity<String> saveMartinsResume(@RequestBody ResumeDTO resumeDTO) {
            resumeService.saveMartinsResume(resumeDTO);
            return new ResponseEntity<>("Resume saved successfully", HttpStatus.OK);
    }

    @GetMapping("/forRecruiters/martinsResume")
    public ResponseEntity<?> getMartinsResume() {
            Resume resumes = resumeRepository.findByMartinsCvIsTrue();
            return new ResponseEntity<>(resumes, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/deleteById/{resumeId}")
    public ResponseEntity<String> deleteById(@PathVariable Long resumeId) {
        resumeService.deleteResume(resumeId);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/allResumes")
    public ResponseEntity<List<Resume>> getAllResumes() {
        List<Resume> resumes = resumeRepository.findAll();
        return new ResponseEntity<>(resumes, HttpStatus.OK);
    }

}
