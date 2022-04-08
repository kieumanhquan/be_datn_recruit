package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.service.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constants.Api.Path.USER)
public class ProfilesController {

    @Autowired
    private ProfilesService profilesService;

    @PostMapping(value = "/profiles")
    public ResponseEntity<Profiles> add(@RequestBody Profiles profiles){
        System.out.println(profiles.toString());
        return ResponseEntity.ok().body( profilesService.save(profiles));
    }

    @GetMapping("/profiles/id={id}")
    public ResponseEntity<Profiles> getProfileByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(profilesService.getProfileByUserId(id));
    }
}
