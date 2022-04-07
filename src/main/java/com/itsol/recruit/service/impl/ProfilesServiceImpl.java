package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.repository.ProfilesRepository;
import com.itsol.recruit.service.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfilesServiceImpl implements ProfilesService {

    @Autowired
    private ProfilesRepository profilesRepository;

    @Override
    public Profiles save(Profiles profiles){
        return profilesRepository.save(profiles);
    }
}
