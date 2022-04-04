package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.*;
import com.itsol.recruit.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class JobFiledController {
    @Autowired
    private JobPositionRepository jobPositionRepository;

    @Autowired
    private StatusJobRepository statusJobRepository;

    @Autowired
    private WorkingFormRepository workingFormRepository;

    @Autowired
    private AcademicLevelRepository academicLevelRepository;

    @Autowired
    private RankRepository rankRepository;

    @GetMapping("/statusJobs")
    public List<StatusJob> findAllStatusJob() {
        return statusJobRepository.findAll();
    }

    @GetMapping("/jobPositions")
    public List<JobPosition> findAllJobPosition() {
        return jobPositionRepository.findAll();
    }

    @GetMapping("/workingForms")
    public List<WorkingForm> findAllWorkingForm() {
        return workingFormRepository.findAll();
    }

    @GetMapping("/academicLevels")
    public List<AcademicLevel> findAllAcademicLevels() {
        return academicLevelRepository.findAll();
    }

    @GetMapping("/ranks")
    public List<Rank> findAllRanks() {
        return rankRepository.findAll();
    }


}
