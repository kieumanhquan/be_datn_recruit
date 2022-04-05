package com.itsol.recruit.repository.repoext;

import com.itsol.recruit.dto.JobPaginationDto;
import com.itsol.recruit.web.vm.SearchJobVM;

import java.util.List;

public interface JobRepositoryExt {
    JobPaginationDto search(SearchJobVM searchJobVM, Integer pageNumber, Integer pageSize);
}
