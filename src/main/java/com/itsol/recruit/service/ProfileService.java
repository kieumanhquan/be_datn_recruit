package com.itsol.recruit.service;

import com.itsol.recruit.entity.Profiles;

public interface ProfileService {
    Profiles getProfileByUserId(Long id);
}
