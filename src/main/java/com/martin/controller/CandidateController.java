package com.martin.controller;

import com.martin.entity.person.Candidate;
import com.martin.entity.person.Skill;
import com.martin.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("/create")
    public ResponseEntity<Candidate> createCandidateWithDetails(@RequestBody Candidate candidate) {
        Candidate createdCandidate = candidateService.createCandidateWithDetails(candidate);
        return new ResponseEntity<>(createdCandidate, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Candidate>> getAllWithDetails() {
        return new ResponseEntity<>(candidateService.allCandidates(), HttpStatus.OK);
    }    @GetMapping("/s")
    public ResponseEntity<List<Skill>> getAllWithDetailsSkills() {
        return new ResponseEntity<>(candidateService.allCandidatesSkills(), HttpStatus.OK);
    }


}
