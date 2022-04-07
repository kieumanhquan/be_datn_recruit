package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.service.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class ProfilesController {

    @Autowired
    private ProfilesService profilesServicel;

    @PostMapping(value = "/profiles")
    public ResponseEntity<Profiles> add(@RequestBody Profiles profiles){
        return ResponseEntity.ok().body( profilesServicel.save(profiles));
    }

    @PutMapping(value = "/profiles")
    public ResponseEntity<Profiles> update(@RequestBody Profiles profiles){
        return ResponseEntity.ok().body( profilesServicel.save(profiles));
    }
}
