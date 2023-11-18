package com.martin.service.resume;

import com.martin.entity.dataTransferObjects.ResumeDTO;
import org.springframework.stereotype.Service;

@Service
public interface ResumeService {

    void saveResume(ResumeDTO resumeDTO);
    void saveMartinsResume(ResumeDTO resumeDTO);

    void deleteResume(Long resumeId);

    void updateRelatedEntities(ResumeDTO resumeDTO, Long id);
}
