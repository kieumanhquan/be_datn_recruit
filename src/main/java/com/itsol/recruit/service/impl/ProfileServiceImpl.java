package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.repository.ProfilesRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfilesRepository profilesRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Profiles getProfileByUserId(Long id){
        return profilesRepository.findOneByUser(userRepository.findOneById(id));
    }
}
