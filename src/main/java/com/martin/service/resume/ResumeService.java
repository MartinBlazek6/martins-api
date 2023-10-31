package com.martin.service.resume;

import com.martin.entity.records.ResumeDTO;
import org.springframework.stereotype.Service;

@Service
public interface ResumeService {

    void saveResume(ResumeDTO resumeDTO);
}
