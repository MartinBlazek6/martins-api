package com.martin.service.resume;

import com.martin.entity.candidate.*;
import com.martin.entity.dataTransferObjects.ResumeDTO;
import com.martin.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResumeServiceImplTest {

    @Mock
    private ResumeRepository resumeRepository;

    @Mock
    private PersonalDetailsRepository personalDetailsRepository;

    @Mock
    private EmploymentRepository employmentRepository;

    @Mock
    private EducationRepository educationRepository;

    @Mock
    private HardSkillsRepository hardSkillsRepository;

    @Mock
    private SoftSkillsRepository softSkillsRepository;

    @InjectMocks
    private ResumeServiceImpl resumeService;

    @Test
    public void testSaveResume() {
        ResumeDTO resumeDTO = new ResumeDTO();
        Resume resume = new Resume();
        resumeDTO.setResume(resume);
        PersonalDetails personalDetails = new PersonalDetails();
        resumeDTO.setPersonalDetails(personalDetails);

        when(resumeRepository.save(any(Resume.class))).thenReturn(new Resume());
        when(personalDetailsRepository.save(any(PersonalDetails.class))).thenReturn(new PersonalDetails());

        resumeService.saveResume(resumeDTO);

        verify(resumeRepository, times(2)).save(any(Resume.class));
        verify(personalDetailsRepository).save(any(PersonalDetails.class));
    }


    @Test
    public void testDeleteResume() {
        Long resumeId = 1L;
        Resume resume = new Resume();
        PersonalDetails personalDetails = new PersonalDetails();
        Employment employment = new Employment();
        Education education = new Education();
        HardSkills hardSkills = new HardSkills();
        SoftSkills softSkills = new SoftSkills();

        resume.setPersonalDetails(personalDetails);
        resume.setEmployments(Collections.singletonList(employment));
        resume.setEducations(Collections.singletonList(education));
        resume.setHardSkills(Collections.singletonList(hardSkills));
        resume.setSoftSkills(Collections.singletonList(softSkills));

        when(resumeRepository.findById(resumeId)).thenReturn(Optional.of(resume));

        resumeService.deleteResume(resumeId);

        verify(personalDetailsRepository).delete(personalDetails);
        verify(employmentRepository).deleteAll(Collections.singletonList(employment));
        verify(educationRepository).deleteAll(Collections.singletonList(education));
        verify(hardSkillsRepository).deleteAll(Collections.singletonList(hardSkills));
        verify(softSkillsRepository).deleteAll(Collections.singletonList(softSkills));
    }


    @Test
    public void testUserSaveResume() {
        ResumeDTO resumeDTO = createMockResumeDTO();

        when(resumeRepository.save(any())).thenReturn(mock(Resume.class));
        when(personalDetailsRepository.save(any())).thenReturn(mock(PersonalDetails.class));

        resumeService.saveResume(resumeDTO);

        verify(resumeRepository, times(2)).save(any());
        verify(personalDetailsRepository, times(1)).save(any());
        assertFalse(resumeDTO.getResume().isMartinsCv());
    }


    @Test
    public void testAdminSaveResume() {
        ResumeDTO resumeDTO = createMockResumeDTO();

        when(resumeRepository.save(any())).thenReturn(mock(Resume.class));
        when(personalDetailsRepository.save(any())).thenReturn(mock(PersonalDetails.class));

        resumeService.saveMartinsResume(resumeDTO);

        verify(resumeRepository, times(2)).save(any());
        verify(personalDetailsRepository, times(1)).save(any());
        assertTrue(resumeDTO.getResume().isMartinsCv());
    }


    private ResumeDTO createMockResumeDTO() {
        ResumeDTO resumeDTO = new ResumeDTO();
        Resume resume = new Resume();
        PersonalDetails personalDetails = new PersonalDetails();
        Employment employment = new Employment();
        Education education = new Education();
        HardSkills hardSkills = new HardSkills();
        SoftSkills softSkills = new SoftSkills();

        resumeDTO.setPersonalDetails(personalDetails);
        resumeDTO.setResume(resume);
        resumeDTO.setEmployments(Collections.singletonList(employment));
        resumeDTO.setEducations(Collections.singletonList(education));
        resumeDTO.setHardSkills(Collections.singletonList(hardSkills));
        resumeDTO.setSoftSkills(Collections.singletonList(softSkills));
        return resumeDTO;
    }


}
