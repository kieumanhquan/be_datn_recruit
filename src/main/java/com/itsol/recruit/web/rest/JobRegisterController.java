package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.JobRegisterPaginationDto;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.service.JobRegisterService;
import com.itsol.recruit.service.ProfileService;
import com.itsol.recruit.web.vm.SearchJobRegisterVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC+"/job-registers")
public class JobRegisterController {
    @Autowired
    private JobRegisterService jobRegisterService;

    @Autowired
    private ProfileService profileService;

    @PostMapping("/searches")
    public JobRegisterPaginationDto find(@RequestBody SearchJobRegisterVM searchJobRegisterVM,@RequestParam(name = "page") int pageNumber, @RequestParam(name = "size") int pageSize) {
        return jobRegisterService.find(searchJobRegisterVM, pageNumber, pageSize);
    }

    @PostMapping("/searches/sortByName")
    public JobRegisterPaginationDto sortByName(@RequestBody SearchJobRegisterVM searchJobRegisterVM,@RequestParam(name = "page") int pageNumber, @RequestParam(name = "size") int pageSize) {
        return jobRegisterService.sortByName(searchJobRegisterVM, pageNumber, pageSize);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<JobRegister> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(jobRegisterService.getById(id));
    }

    @GetMapping("/profiles/id={id}")
    public ResponseEntity<Profiles> getProfileByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(profileService.getProfileByUserId(id));
    }

    @GetMapping("/jobId={id}")
    public ResponseEntity<List<JobRegister>> getByJobId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(jobRegisterService.getByJobId(id));
    }

}
