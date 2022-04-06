package com.itsol.recruit.dto;

import com.itsol.recruit.entity.JobRegister;
import lombok.Data;
import java.util.List;

@Data
public class JobRegisterPaginationDto {
    List<JobRegister> list;

    Long totalPage;
}
