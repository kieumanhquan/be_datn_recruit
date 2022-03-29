package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.OtpRepository;
import com.itsol.recruit.service.OtpService;
import com.itsol.recruit.service.UserService;
import com.itsol.recruit.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class OtpServiceImpl implements OtpService {
     @Autowired
     OtpRepository otpRepository;
     @Autowired
     UserService userService;
    @Autowired
    EmailService emailService;

    @Override
    public List<OTP> getAll() {
        return null;
    }

    @Override
    public OTP findById(Long id) {
        return otpRepository.getById(id);
    }

    @Override
     public OTP findByUserId(Long id){
        return new OTP();
    }
    @Override
    public boolean save(OTP otp) {
        try {
            otpRepository.save(otp);
            return true;
        }
        catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean edit(OTP otp) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean check(OTP otp, User user) {
        return false;
    }

    @Override
    public boolean sendOtp(UserDTO userDTO) {
      try{
          User user=  userService.findByEmail(userDTO.getEmail());
           OTP otp= new OTP(user);
           otpRepository.save(otp);
           String email=emailService.buildOtpEmail(user.getName(),otp.getCode());
           emailService.send(user.getEmail(),email);
           return true;
      }catch (Exception e){
          System.out.println(e);
          return false;
        }
    }
}
