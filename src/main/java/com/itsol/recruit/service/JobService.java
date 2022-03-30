package com.itsol.recruit.service;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.dto.JobPaginationDto;
import com.itsol.recruit.entity.Job;

import java.util.List;

public interface JobService {
    List<Job> find(String name, int numberExperience, int salaryMin, int salaryMax, int pageNumber, int pageSize);

    JobPaginationDto findTest(String name, Long statusId, int salaryMin, int salaryMax, int pageNumber, int pageSize);

    Job add(JobDTO jobDTO);

    Job update(JobDTO jobDTO);

    Job getById(Long id);

    List<Job> findAll();
}
