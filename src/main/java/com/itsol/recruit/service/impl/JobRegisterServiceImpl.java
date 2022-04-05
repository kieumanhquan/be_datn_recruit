package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.JobRegisterPaginationDto;
import com.itsol.recruit.dto.ScheduleDto;
import com.itsol.recruit.dto.StatusRegisterDto;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.StatusJobRegisterRepository;
import com.itsol.recruit.service.JobRegisterService;
import com.itsol.recruit.service.email.EmailService;
import com.itsol.recruit.web.vm.SearchJobRegisterVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobRegisterServiceImpl implements JobRegisterService {

    @Autowired
    private JobRegisterRepository jobRegisterRepository;

    @Autowired
    private StatusJobRegisterRepository statusJobRegisterRepository;

    @Autowired
    private EmailService emailService;


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

    @Override
    public JobRegister updateStatus(StatusRegisterDto statusRegisterDto){
       JobRegister jobRegister = jobRegisterRepository.findOneById(statusRegisterDto.getJobRegisterId());
       jobRegister.setStatusJobRegister(statusJobRegisterRepository.findOneById(statusRegisterDto.getStatusRegisterId()));
        return jobRegisterRepository.save(jobRegister);
    }

    @Override
    public JobRegister schedule(ScheduleDto scheduleDto){
        JobRegister jobRegister = jobRegisterRepository.findOneById(scheduleDto.getJobRegisterId());
        jobRegister.setStatusJobRegister(statusJobRegisterRepository.findOneById(scheduleDto.getStatusRegisterId()));
        jobRegister.setDateInterview(scheduleDto.getDateInterview());
        jobRegister.setMethodInterview(scheduleDto.getMethodInterview());
        jobRegister.setAddressInterview(scheduleDto.getAddressInterview());
        User user = jobRegister.getUser();
        String link=emailService.builSchedule(jobRegister);
            emailService.send(user.getEmail(),link);
        return jobRegisterRepository.save(jobRegister);
    }

    @Override
    public Resource downloadCv(Long applicantId) throws IOException {
        JobRegister jobRegister = jobRegisterRepository.getById(applicantId);
        if (ObjectUtils.isEmpty(jobRegister)) {
            throw new NullPointerException("Could not found applicant");
        }
        String cvFilePath = jobRegister.getCv();
        Path file = Paths.get(cvFilePath);
        Resource resource = new UrlResource(file.toUri());

        if (!resource.exists() && !resource.isReadable()) {
            throw new RuntimeException("Could not read the file!");
        }
        return resource;
    }
}
