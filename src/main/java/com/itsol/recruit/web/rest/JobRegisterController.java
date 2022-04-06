package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.JobRegisterPaginationDto;
import com.itsol.recruit.dto.ScheduleDto;
import com.itsol.recruit.dto.StatusRegisterDto;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.service.JobRegisterService;
import com.itsol.recruit.service.ProfileService;
import com.itsol.recruit.web.vm.ResponseMessage;
import com.itsol.recruit.web.vm.SearchJobRegisterVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
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

    @PutMapping("/status_job")
    public ResponseEntity<JobRegister> updateStatusJob(@RequestBody StatusRegisterDto statusRegisterDto) {
        return ResponseEntity.ok().body(jobRegisterService.updateStatus(statusRegisterDto));
    }

    @PutMapping("/schedule")
    public ResponseEntity<JobRegister> schedule(@RequestBody ScheduleDto scheduleDto) {
        return ResponseEntity.ok().body(jobRegisterService.schedule(scheduleDto));
    }

    @GetMapping("/download/{id}")
    @CrossOrigin
    public ResponseEntity<Resource> downloadApplicantCv(@PathVariable("id") Long id) throws Exception {
        Resource resource = jobRegisterService.downloadCv(id);
        Path path = resource.getFile()
                .toPath();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
