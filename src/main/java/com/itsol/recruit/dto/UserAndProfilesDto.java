package com.itsol.recruit.dto;

import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.entity.User;
import lombok.Data;
@Data
public class UserAndProfilesDto {

    User user;

    Profiles profiles;

}
