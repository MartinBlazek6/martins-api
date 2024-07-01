package com.martin.controller;

import com.martin.entity.candidate.Resume;
import com.martin.entity.dataTransferObjects.ResumeDTO;
import com.martin.repositories.ResumeRepository;
import com.martin.service.resume.ResumeService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = "User Management API")
public class ResumeController {

    private final ResumeRepository resumeRepository;
    private final ResumeService resumeService;

    @ApiOperation(value = "Save a new resume", notes = "Endpoint to save a new resume", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resume saved successfully"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    @PostMapping("/saveResume")
    public ResponseEntity<String> saveResume(
            @ApiParam(value = "ResumeDTO object containing resume details", required = true)
            @RequestBody ResumeDTO resumeDTO) {
        resumeService.saveResume(resumeDTO);
        return new ResponseEntity<>("Resume saved successfully", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an existing resume", notes = "Endpoint to update an existing resume", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resume updated successfully"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 403, message = "Access denied")
    })
    @PreAuthorize("hasRole('Admin')")
    @PatchMapping("/updateResume")
    public ResponseEntity<String> updateResume(
            @ApiParam(value = "ResumeDTO object containing updated resume details", required = true)
            @RequestBody ResumeDTO resumeDTO,
            @ApiParam(value = "ID of the resume to be updated", required = true)
            @RequestParam Long resumeId) {
        resumeService.updateRelatedEntities(resumeDTO, resumeId);
        return new ResponseEntity<>("Resume updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Save Martin's resume", notes = "Endpoint to save Martin's resume", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resume saved successfully"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 403, message = "Access denied")
    })
    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/saveMartinsResume")
    public ResponseEntity<String> saveMartinsResume(
            @ApiParam(value = "ResumeDTO object containing resume details", required = true)
            @RequestBody ResumeDTO resumeDTO) {
        resumeService.saveMartinsResume(resumeDTO);
        return new ResponseEntity<>("Resume saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Get Martin's resume", notes = "Endpoint to get Martin's resume", response = Resume.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resume retrieved successfully"),
            @ApiResponse(code = 404, message = "Resume not found")
    })
    @GetMapping("/forRecruiters/martinsResume")
    public ResponseEntity<Resume> getMartinsResume() {
        Resume resume = resumeRepository.findByMartinsCvIsTrue();
        return new ResponseEntity<>(resume, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a resume by ID", notes = "Endpoint to delete a resume by ID", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Resume deleted successfully"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Resume not found")
    })
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/deleteById/{resumeId}")
    public ResponseEntity<String> deleteById(
            @ApiParam(value = "ID of the resume to be deleted", required = true)
            @PathVariable Long resumeId) {
        resumeService.deleteResume(resumeId);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get all resumes", notes = "Endpoint to get all resumes", response = Resume.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resumes retrieved successfully"),
            @ApiResponse(code = 403, message = "Access denied")
    })
    @PreAuthorize("hasRole('User')")
    @GetMapping("/allResumes")
    public ResponseEntity<List<Resume>> getAllResumes() {
        List<Resume> resumes = resumeRepository.findAll();
        return new ResponseEntity<>(resumes, HttpStatus.OK);
    }
}
