package com.itsol.recruit.service.mapper;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class JobMapper implements EntityMapper<JobDTO, Job> {

    @Autowired
    private JobPositionRepository jobPositionRepository;
    @Autowired
    private WorkingFormRepository workingFormRepository;
    @Autowired
    private AcademicLevelRepository academicLevelRepository;
    @Autowired
    private RankRepository rankRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusJobRepository statusJobRepository;


    @SneakyThrows
    @Override
    public Job toEntity(JobDTO dto) {
        if (dto == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Job job = new Job();
        job.setName(dto.getName());
        job.setNumberExperience(dto.getNumberExperience());
        job.setAddressWork(dto.getAddressWork());
        job.setJobPosition(jobPositionRepository.findOneById(dto.getJobPositionId()));
        job.setWorkingForm(workingFormRepository.findOneById(dto.getWorkingFormId()));
        job.setAcademicLevel(academicLevelRepository.findOneById(dto.getAcademicLevelId()));
        job.setRank(rankRepository.findOneById(dto.getRankId()));
        job.setQtyPerson(dto.getQtyPerson());
        job.setCreateDate(new Date());
        job.setStartRecruitmentDate(dto.getStartRecruitmentDate());
        job.setDueDate(dto.getDueDate());
        job.setSkills(dto.getSkills());
        job.setDescription(dto.getDescription());
        job.setBenefits(dto.getBenefits());
        job.setJobRequirement(dto.getJobRequirement());
        job.setSalaryMin(dto.getSalaryMin());
        job.setSalaryMax(dto.getSalaryMax());
        job.setContact(userRepository.findOneById(dto.getContactId()));
        job.setCreator(userRepository.findOneById(dto.getCreatorId()));
        job.setUpdateUser(userRepository.findOneById(dto.getUpdateUserId()));
        job.setUpdateDate(dto.getUpdateDate());
        job.setStatusJob(statusJobRepository.findOneById(dto.getStatusJobId()));
        job.setViews(dto.getViews());
        job.setDelete(false);
        return job;
    }

    @SneakyThrows
    public Job toEntity(JobDTO dto,Job job) {
        if (dto == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        job.setName(dto.getName());
        job.setNumberExperience(dto.getNumberExperience());
        job.setAddressWork(dto.getAddressWork());
        job.setJobPosition(jobPositionRepository.findOneById(dto.getJobPositionId()));
        job.setWorkingForm(workingFormRepository.findOneById(dto.getWorkingFormId()));
        job.setAcademicLevel(academicLevelRepository.findOneById(dto.getAcademicLevelId()));
        job.setRank(rankRepository.findOneById(dto.getRankId()));
        job.setQtyPerson(dto.getQtyPerson());
        job.setStartRecruitmentDate(dto.getStartRecruitmentDate());
        job.setDueDate(dto.getDueDate());
        job.setSkills(dto.getSkills());
        job.setDescription(dto.getDescription());
        job.setBenefits(dto.getBenefits());
        job.setJobRequirement(dto.getJobRequirement());
        job.setSalaryMin(dto.getSalaryMin());
        job.setSalaryMax(dto.getSalaryMax());
        job.setContact(userRepository.findOneById(dto.getContactId()));
        job.setUpdateUser(userRepository.findOneById(dto.getUpdateUserId()));
        job.setUpdateDate(dto.getUpdateDate());
        job.setStatusJob(statusJobRepository.findOneById(dto.getStatusJobId()));
        job.setViews(dto.getViews());
        job.setDelete(false);
        return job;
    }

    @Override
    public JobDTO toDto(Job entity) {
        return null;
    }

    @Override
    public List<Job> toEntity(List<JobDTO> dtoList) {
        return null;
    }

    @Override
    public List<JobDTO> toDto(List<Job> entityList) {
        return null;
    }
}
