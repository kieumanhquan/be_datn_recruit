package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.StatusJob;
import com.itsol.recruit.repository.StatusJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class StatusJobController {

    @Autowired
    private StatusJobRepository statusJobRepository;

    @GetMapping("/statusJobs")
    public List<StatusJob> findAllStatusJob() {
        return statusJobRepository.findAll();
    }
}
