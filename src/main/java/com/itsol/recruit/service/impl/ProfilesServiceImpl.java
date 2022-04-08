package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.repository.ProfilesRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfilesServiceImpl implements ProfilesService {

    @Autowired
    private ProfilesRepository profilesRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Profiles save(Profiles profiles){
        return profilesRepository.save(profiles);
    }

    @Override
    public Profiles getProfileByUserId(Long id){
        Profiles profiles=profilesRepository.findOneByUser(userRepository.findOneById(id));
        if(profiles==null){
            return new Profiles(userRepository.findOneById(id));
        }else{
        return profiles;}
    }


}
