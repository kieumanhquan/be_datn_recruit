package com.itsol.recruit.service;


import com.itsol.recruit.dto.MessageDto;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.User;

public interface AuthenticateService {
     Boolean signup(UserDTO dto);

    MessageDto changePassword(UserDTO dto);

    Boolean activeAccount(String otp, Long userId);

}
