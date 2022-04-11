package com.itsol.recruit.service;

import com.itsol.recruit.dto.JobRegisterPaginationDto;
import com.itsol.recruit.dto.ReasonDto;
import com.itsol.recruit.dto.ScheduleDto;
import com.itsol.recruit.dto.StatusRegisterDto;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.web.vm.SearchJobRegisterVM;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface JobRegisterService {
    JobRegister save(JobRegister jobRegister);

    JobRegisterPaginationDto find(SearchJobRegisterVM searchJobRegisterVM, int pageNumber, int pageSize);

    JobRegisterPaginationDto sortByName(SearchJobRegisterVM searchJobRegisterVM, int pageNumber, int pageSize);

    JobRegister getById(Long id);

    List<JobRegister> getByJobId(Long id);

    JobRegister updateStatus(StatusRegisterDto statusRegisterDto);

    JobRegister schedule(ScheduleDto scheduleDto);

    Resource downloadCv(Long applicantId) throws IOException;

    JobRegister updateReason(ReasonDto reasonDto);

    JobRegister findByUserAndJob(Long userId, Long jobId);
}
