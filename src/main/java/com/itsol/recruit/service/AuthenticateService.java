package com.itsol.recruit.service;


import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.User;

public interface AuthenticateService {
     Boolean signup(UserDTO dto);

    Boolean changePassword(UserDTO dto);

    Boolean activeAccount(String otp, Long userId);

}
