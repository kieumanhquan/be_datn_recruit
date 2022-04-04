package com.itsol.recruit.service;

import com.itsol.recruit.dto.JobRegisterPaginationDto;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.web.vm.SearchJobRegisterVM;

import java.util.List;

public interface JobRegisterService {
    JobRegisterPaginationDto find(SearchJobRegisterVM searchJobRegisterVM, int pageNumber, int pageSize);

    JobRegisterPaginationDto sortByName(SearchJobRegisterVM searchJobRegisterVM, int pageNumber, int pageSize);

    JobRegister getById(Long id);

    List<JobRegister> getByJobId(Long id);
}
