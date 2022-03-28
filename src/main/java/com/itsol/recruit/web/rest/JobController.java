package com.itsol.recruit.web.rest;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/searches")
    public List<Job> find(@RequestParam(name = "name") String name, @RequestParam(name = "numberExperience") int numberExperience,
                          @RequestParam(name = "salaryMin") int salaryMin, @RequestParam(name = "salaryMax") int salaryMax,
                          @RequestParam(name = "page") int pageNumber, @RequestParam(name = "size") int pageSize) {
        return jobService.find(name, numberExperience, salaryMin, salaryMax, pageNumber, pageSize);
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
        return ResponseEntity.ok().body(jobService.update(jobDTO));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Job> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(jobService.getById(id));
    }


}
