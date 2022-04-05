package com.itsol.recruit.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduleDto {
    Long jobRegisterId;

    Long statusRegisterId;

    Date dateInterview;

    private String methodInterview;

    private String addressInterview;
}
