package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.JobRegisterPaginationDto;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.service.JobRegisterService;
import com.itsol.recruit.web.vm.SearchJobRegisterVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobRegisterServiceImpl implements JobRegisterService {

    @Autowired
    private JobRegisterRepository jobRegisterRepository;

    @Override
    public JobRegisterPaginationDto find(SearchJobRegisterVM searchJobRegisterVM, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        JobRegisterPaginationDto jobRegisterPaginationDto =  new JobRegisterPaginationDto();
        jobRegisterPaginationDto.setList(jobRegisterRepository.find("%"+searchJobRegisterVM.getName().toLowerCase()+"%", searchJobRegisterVM.getStatusRegisterId(), pageable).stream().collect(Collectors.toList()));
        jobRegisterPaginationDto.setTotalPage((long) jobRegisterRepository.find("%"+searchJobRegisterVM.getName().toLowerCase()+"%", searchJobRegisterVM.getStatusRegisterId(), pageable).getTotalPages());
        return jobRegisterPaginationDto;
    }

    @Override
    public JobRegisterPaginationDto sortByName(SearchJobRegisterVM searchJobRegisterVM, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        JobRegisterPaginationDto jobRegisterPaginationDto =  new JobRegisterPaginationDto();
        jobRegisterPaginationDto.setList(jobRegisterRepository.sortByName("%"+searchJobRegisterVM.getName().toLowerCase()+"%", searchJobRegisterVM.getStatusRegisterId(), pageable).stream().collect(Collectors.toList()));
        jobRegisterPaginationDto.setTotalPage((long) jobRegisterRepository.sortByName("%"+searchJobRegisterVM.getName().toLowerCase()+"%", searchJobRegisterVM.getStatusRegisterId(), pageable).getTotalPages());
        return jobRegisterPaginationDto;
    }

    @Override
    public JobRegister getById(Long id) {
        return jobRegisterRepository.findOneById(id);
    }

    @Override
    public List<JobRegister> getByJobId(Long id) {
        return jobRegisterRepository.findByJobId(id);
    }
}
