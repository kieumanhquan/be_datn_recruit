package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.AcademicLevel;
import com.itsol.recruit.repository.AcademicLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class AcademicLevelController {
    @Autowired
    private AcademicLevelRepository academicLevelRepository;

    @GetMapping("/academicLevels")
    public List<AcademicLevel> findAllAcademicLevels() {
        return academicLevelRepository.findAll();
    }
}
