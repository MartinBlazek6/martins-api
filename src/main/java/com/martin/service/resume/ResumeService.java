package com.martin.service.resume;

import com.martin.entity.candidate.Resume;
import com.martin.entity.records.ResumeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {

    void saveResume(ResumeDTO resumeDTO);

    <T, ID> void saveRelatedEntities(Resume resume, List<T> entities, JpaRepository<T, ID> repository);
}
