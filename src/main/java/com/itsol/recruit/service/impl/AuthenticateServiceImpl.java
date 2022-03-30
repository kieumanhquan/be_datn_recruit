package com.itsol.recruit.service.impl;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.AuthenticateRepository;
import com.itsol.recruit.repository.OtpRepository;
import com.itsol.recruit.repository.RoleRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.AuthenticateService;
import com.itsol.recruit.service.email.EmailService;
import com.itsol.recruit.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {

    public final AuthenticateRepository authenticateRepository;

    public final UserMapper userMapper;

    public final RoleRepository roleRepository;

    public final UserRepository userRepository;

    public final EmailService emailService;

    @Autowired
     OtpRepository otpRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AuthenticateServiceImpl(AuthenticateRepository authenticateRepository, UserMapper userMapper, RoleRepository roleRepository, UserRepository userRepository, EmailService emailService) {
        this.authenticateRepository = authenticateRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.emailService =emailService;
    }

    @Override
    public Boolean signup(UserDTO dto) {
        try{
            Set<Role> roles = roleRepository.findByCode(Constants.Role.USER);
            User user = userMapper.toEntity(dto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActive(false);
            user.setRoles(roles);
            userRepository.save(user);
            OTP otp=new OTP(user);
            otpRepository.save(otp);
            String link=emailService.buildActiveEmail(user.getName(),otp.getCode(),user.getId());
            emailService.send(user.getEmail(),link);
//        String linkActive = accountActivationConfig.getActivateUrl() + user.getId();
//        emailService.sendSimpleMessage(user.getEmail(),
//                "Link active account",
//                "<a href=\" " + linkActive + "\">Click vào đây để kích hoạt tài khoản</a>");*/
            return true;
        }catch (Exception e){
            log.error("cannot save to database");
            return  false;
        }

    }

    @Override
    public Boolean changePassword(UserDTO dto) {
        try{
            Optional<User> user= userRepository.findByEmail(dto.getEmail());

            if(user.isPresent()){
                OTP dbOtp= otpRepository.findByUser(user.get());
                User tempUser=user.get();
                System.out.println("old pass: "+ tempUser.getPassword());
                if(dbOtp.isExpired()){
                    System.out.println("het han");
                    return false;
                }else{
                    if(dbOtp.getCode().equals(dto.getOtp())){
                        tempUser.setPassword(passwordEncoder.encode(dto.getPassword()));
                        System.out.println("new pass : "+ tempUser.getPassword());
                    userRepository.save(tempUser);
                    return true;
                    }else {
                        System.out.println(dbOtp.getCode());
                        System.out.println(dto.getOtp());
                        System.out.println("sai code");
                        return false;
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public Boolean activeAccount(String otp, Long userId) {
        try{
            Optional<User> dbUser=userRepository.findById(userId);
            if(dbUser.isPresent()){
                User user=dbUser.get();
                OTP dbOtp= otpRepository.findByUser(user);
                if(dbOtp.getCode().equals(otp)){
                   user.setActive(true);
                   userRepository.save(user);
                    return true;
                }

            }else{return false;}
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

}
