package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.WorkingForm;
import com.itsol.recruit.repository.WorkingFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class WorkingFormController {

    @Autowired
    private WorkingFormRepository workingFormRepository;

    @GetMapping("/workingForms")
    public List<WorkingForm> findAllWorkingForm() {
        return workingFormRepository.findAll();
    }
}
