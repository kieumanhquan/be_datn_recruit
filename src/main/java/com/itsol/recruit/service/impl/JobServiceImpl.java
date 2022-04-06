package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.dto.JobPaginationDto;
import com.itsol.recruit.dto.ReasonDto;
import com.itsol.recruit.dto.StatusDto;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.repository.StatusJobRepository;
import com.itsol.recruit.repository.repoext.JobRepositoryExt;
import com.itsol.recruit.service.JobService;
import com.itsol.recruit.service.mapper.JobMapper;
import com.itsol.recruit.web.vm.SearchJobVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private JobRepositoryExt jobRepositoryExt;

    @Autowired
    private StatusJobRepository statusJobRepository;


//    @Override
//    public JobPaginationDto find(SearchJobVM searchJobVM, int pageNumber, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        JobPaginationDto jobPaginationDto = new JobPaginationDto();
//        jobPaginationDto.setList(jobRepository.find("%" + searchJobVM.getName().toLowerCase() + "%",
//                searchJobVM.getStatusId(), searchJobVM.getSalaryMin(), searchJobVM.getSalaryMax(),
//                pageable).stream().collect(Collectors.toList()));
//
//        jobPaginationDto.setTotalPage((long) jobRepository.find("%" + searchJobVM.getName().toLowerCase() + "%",
//                searchJobVM.getStatusId(), searchJobVM.getSalaryMin(),
//                searchJobVM.getSalaryMax(), pageable).getTotalPages());
//        return jobPaginationDto;
//    }
//
    @Override
    public JobPaginationDto find(SearchJobVM searchJobVM, int pageNumber, int pageSize) {
        return jobRepositoryExt.search(searchJobVM,pageNumber,pageSize);
    }

    @Override
    public JobPaginationDto sortByName(SearchJobVM searchJobVM, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        JobPaginationDto jobPaginationDto = new JobPaginationDto();
        jobPaginationDto.setList(jobRepository.sortByName("%" + searchJobVM.getName().toLowerCase() + "%",
                searchJobVM.getStatusId(), searchJobVM.getSalaryMin(), searchJobVM.getSalaryMax(),
                pageable).stream().collect(Collectors.toList()));
        jobPaginationDto.setTotalPage((long) jobRepository.sortByName("%" + searchJobVM.getName().toLowerCase() + "%",
                searchJobVM.getStatusId(), searchJobVM.getSalaryMin(), searchJobVM.getSalaryMax(),
                pageable).getTotalPages());
        return jobPaginationDto;
    }

    @Override
    public Job add(JobDTO jobDTO) {
        if (jobDTO.getId() == null) {
            Job job = jobMapper.toEntity(jobDTO);
            return jobRepository.save(job);
        } else {
            Job jobExits = jobRepository.findOneById(jobDTO.getId());
            Job job = jobMapper.toEntity(jobDTO, jobExits);
            return jobRepository.save(job);
        }

    }

    @Override
    public Job getById(Long id) {
        return jobRepository.findOneById(id);
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public JobPaginationDto getNewJob(Integer numberDay, int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        JobPaginationDto jobPaginationDto = new JobPaginationDto();

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -numberDay);
        dt = c.getTime();

        jobPaginationDto.setList(jobRepository.getJobNew(dt,pageable).stream().collect(Collectors.toList()));
        jobPaginationDto.setTotalPage((long) jobRepository.getJobNew(dt,pageable).getTotalPages());
        return jobPaginationDto;
    }

    @Override
    public JobPaginationDto getJobHighSalary(Integer salary, int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        JobPaginationDto jobPaginationDto = new JobPaginationDto();
        jobPaginationDto.setList(jobRepository.getJobHighSalary(salary,pageable).stream().collect(Collectors.toList()));
        jobPaginationDto.setTotalPage((long) jobRepository.getJobHighSalary(salary,pageable).getTotalPages());
        return jobPaginationDto;
    }

    @Override
    public JobPaginationDto getJobDue(Integer numberDay, int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        JobPaginationDto jobPaginationDto = new JobPaginationDto();

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, numberDay);
        dt = c.getTime();

        jobPaginationDto.setList(jobRepository.getJobDue(dt,pageable).stream().collect(Collectors.toList()));
        jobPaginationDto.setTotalPage((long) jobRepository.getJobDue(dt,pageable).getTotalPages());
        return jobPaginationDto;
    }

    @Override
    public Job updateStatus(StatusDto statusDto){
        Job job = jobRepository.findOneById(statusDto.getJobId());
        job.setStatusJob(statusJobRepository.findOneById(statusDto.getStatusId()));
        return jobRepository.save(job);
    }

    @Override
    public Job updateReason(ReasonDto reasonDto){
        Job job = jobRepository.findOneById(reasonDto.getJobId());
        job.setStatusJob(statusJobRepository.findOneById(reasonDto.getStatusId()));
        job.setReason(reasonDto.getReason());
        return jobRepository.save(job);
    }
}
