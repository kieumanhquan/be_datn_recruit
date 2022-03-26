package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.JobJpa;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.service.JobService;
import com.itsol.recruit.service.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobJpa jobJpa;

    @Autowired
    private JobMapper jobMapper;

    @Override
    public List<Job> find(String name, int numberExperience, int salaryMin, int salaryMax, int pageNumber, int pageSize) {
        return jobRepository.find(name, numberExperience, salaryMin, salaryMax, pageNumber, pageSize);
    }

    @Override
    public Job add(JobDTO jobDTO){
        Job job = jobMapper.toEntity(jobDTO);
        return jobJpa.save(job);
    }

    @Override
    public Job update(JobDTO jobDTO){
        Job jobExits = jobJpa.findOneById(jobDTO.getId());
        Job job = jobMapper.toEntity(jobDTO,jobExits);
        return jobJpa.save(job);
    }

    @Override
    public Job getById(Long id){
        return jobJpa.findOneById(id);
    }
}
