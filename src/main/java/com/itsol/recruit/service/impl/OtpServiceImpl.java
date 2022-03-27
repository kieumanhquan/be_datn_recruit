package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.OtpRepository;
import com.itsol.recruit.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class OtpServiceImpl implements OtpService {
     @Autowired
     OtpRepository otpRepository;

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
}
