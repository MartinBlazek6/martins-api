package com.martin.service.resume;

import com.martin.entity.candidate.Resume;
import com.martin.entity.records.ResumeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {

    void saveResume(ResumeDTO resumeDTO);
    void saveMartinsResume(ResumeDTO resumeDTO);

    void deleteResume(Long resumeId);

    void updateRelatedEntities(ResumeDTO resumeDTO, Long id);
}
