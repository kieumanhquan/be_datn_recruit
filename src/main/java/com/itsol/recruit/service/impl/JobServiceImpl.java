package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.dto.JobPaginationDto;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.service.JobService;
import com.itsol.recruit.service.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;


    @Override
    public JobPaginationDto find(String name, Long statusId, int salaryMin, int salaryMax, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        JobPaginationDto jobPaginationDto =  new JobPaginationDto();
        jobPaginationDto.setList(jobRepository.find("%"+name.toLowerCase()+"%", statusId, salaryMin, salaryMax, pageable).stream().collect(Collectors.toList()));
        jobPaginationDto.setTotalPage((long) jobRepository.find("%"+name.toLowerCase()+"%", statusId, salaryMin, salaryMax, pageable).getTotalPages());
        return jobPaginationDto;
    }

    @Override
    public JobPaginationDto sortByName(String name, Long statusId, int salaryMin, int salaryMax, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        JobPaginationDto jobPaginationDto =  new JobPaginationDto();
        jobPaginationDto.setList(jobRepository.sortByName("%"+name.toLowerCase()+"%", statusId, salaryMin, salaryMax, pageable).stream().collect(Collectors.toList()));
        jobPaginationDto.setTotalPage((long) jobRepository.sortByName("%"+name.toLowerCase()+"%", statusId, salaryMin, salaryMax, pageable).getTotalPages());
        return jobPaginationDto;
    }

    @Override
    public Job add(JobDTO jobDTO){
        Job job = jobMapper.toEntity(jobDTO);
        return jobRepository.save(job);
    }

    @Override
    public Job update(JobDTO jobDTO){
        Job jobExits = jobRepository.findOneById(jobDTO.getId());
        Job job = jobMapper.toEntity(jobDTO,jobExits);
        return jobRepository.save(job);
    }

    @Override
    public Job getById(Long id){
        return jobRepository.findOneById(id);
    }

    @Override
    public List<Job> findAll(){
        return jobRepository.findAll();
    }
}
