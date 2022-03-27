package com.itsol.recruit.service;


import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.User;

public interface AuthenticateService {
     User signup(UserDTO dto);

    User changePassword(UserDTO dto, OTP otp);


    User sendOtp(User user);
}
