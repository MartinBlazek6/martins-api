package com.martin.entity.records;

import com.martin.entity.Employment;
import com.martin.entity.Resume;
import lombok.Data;

import java.util.List;

@Data
public class ResumeDTO {
    private Resume resume;
    private List<Employment> employments;
}
