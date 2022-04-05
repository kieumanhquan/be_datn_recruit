package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.dto.JobPaginationDto;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.service.JobService;
import com.itsol.recruit.web.vm.SearchJobVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC + "/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/searches")
    public JobPaginationDto find(@RequestBody SearchJobVM searchJobVM, @RequestParam(name = "page") int pageNumber,
                                 @RequestParam(name = "size") int pageSize) {
        return jobService.find(searchJobVM, pageNumber, pageSize);
    }

    @PostMapping("/searches/sortByName")
    public JobPaginationDto sortByName(@RequestBody SearchJobVM searchJobVM, @RequestParam(name = "page") int pageNumber,
                                       @RequestParam(name = "size") int pageSize) {
        return jobService.find(searchJobVM, pageNumber, pageSize);
    }

    @GetMapping()
    public List<Job> findAll() {
        return jobService.findAll();
    }

    @PostMapping
    public ResponseEntity<Job> add(@Valid @RequestBody JobDTO jobDTO) {
        return ResponseEntity.ok().body(jobService.add(jobDTO));
    }

    @PutMapping
    public ResponseEntity<Job> update(@Valid @RequestBody JobDTO jobDTO) {
        return ResponseEntity.ok().body(jobService.add(jobDTO));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Job> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(jobService.getById(id));
    }


    @GetMapping("/news")
    public ResponseEntity<JobPaginationDto> getNewJob(@RequestParam(name = "numberDay") Integer numberDay,
                                                      @RequestParam(name = "page") int pageNumber,
                                                      @RequestParam(name = "size") int pageSize) {
        return ResponseEntity.ok().body(jobService.getNewJob(numberDay, pageNumber, pageSize));
    }

    @GetMapping("/salary-highs")
    public ResponseEntity<JobPaginationDto> getHigghSalary(@RequestParam(name = "salary") Integer salary,
                                                           @RequestParam(name = "page") int pageNumber,
                                                           @RequestParam(name = "size") int pageSize) {
        return ResponseEntity.ok().body(jobService.getJobHighSalary(salary, pageNumber, pageSize));
    }

    @GetMapping("/due-dates")
    public ResponseEntity<JobPaginationDto> getJobDueDate(@RequestParam(name = "numberDay") Integer numberDay,
                                                          @RequestParam(name = "page") int pageNumber,
                                                          @RequestParam(name = "size") int pageSize) {
        return ResponseEntity.ok().body(jobService.getJobDue(numberDay, pageNumber, pageSize));
    }

    @PostMapping("/status_job")
    public void updateStatusJob(@RequestParam(name = "status_id") Long statusJob,
                                                @RequestParam(name = "job_id") Long id
    ) {
       jobService.updateStatus(id,statusJob);
    }
}
