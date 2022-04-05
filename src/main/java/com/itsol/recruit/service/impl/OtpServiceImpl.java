package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.MessageDto;
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
    public MessageDto sendOtp(UserDTO userDTO) {
        MessageDto messageDto=new MessageDto();
        Boolean obj=false;
      try{
          User user=  userService.findByEmail(userDTO.getEmail());
          if(user==null){
              messageDto.setMessage("Không tìm thấy Email.");
              messageDto.setObj(false);
          }else{
           OTP otp= new OTP(user);
           OTP oldOtp= otpRepository.findOneByUser(user);
           if(oldOtp !=null){
               oldOtp.setCode(otp.getCode());
               oldOtp.setIssueAt(otp.getIssueAt());
               otpRepository.save(oldOtp);
           }
           else{
               otpRepository.save(otp);
           }
           String email=emailService.buildOtpEmail(user.getName(),otp.getCode());
           emailService.send(user.getEmail(),email);
           messageDto.setMessage("Đã gửi mã OTP đến mail: " + user.getEmail() );
           messageDto.setObj(userDTO);
          }
      }catch (Exception e){
          System.out.println(e);
        }
      return messageDto;
    }
}
