package com.itsol.recruit.service.impl;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.MessageDto;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.*;
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

    public final ProfilesRepository profilesRepository;

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AuthenticateServiceImpl(AuthenticateRepository authenticateRepository, UserMapper userMapper, RoleRepository roleRepository, UserRepository userRepository, EmailService emailService, ProfilesRepository profilesRepository) {
        this.authenticateRepository = authenticateRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.profilesRepository = profilesRepository;
    }

    @Override
    public Boolean signup(UserDTO dto) {
        if (!userRepository.findOneByEmail(dto.getEmail()).isPresent() && !userRepository.findByUserName(dto.getUserName()).isPresent() && !userRepository.findByPhoneNumber(dto.getPhoneNumber()).isPresent()) {
            try {
                Set<Role> roles = roleRepository.findByCode(Constants.Role.USER);
                User user = userMapper.toEntity(dto);
                Profiles profiles = new Profiles();
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setActive(false);
                user.setRoles(roles);
                user.setFirstTimeLogin(false);
                userRepository.save(user);
                OTP otp = new OTP(user);
                otpRepository.save(otp);
                String link = emailService.buildActiveEmail(user.getName(), otp.getCode(), user.getId());
                emailService.send(user.getEmail(), link);
                return true;
            } catch (Exception e) {
                log.error("cannot save to database");
                return false;
            }
        } else return false;
    }

    @Override
    public MessageDto changePassword(UserDTO dto) {
        boolean obj = false;
        String message = "";
        try {
            System.out.println("email change pass word: " + dto.getPassword());
            Optional<User> user = userRepository.findOneByEmail(dto.getEmail());
            if (user.isPresent()) {
                OTP dbOtp = otpRepository.findByUser(user.get());
                User tempUser = user.get();
                System.out.println("old pass: " + tempUser.getPassword());
                if (dbOtp.isExpired()) {
                    message = "Mã otp đã hết hạn";
                } else {
                    if (dbOtp.getCode().equals(dto.getOtp())) {
                        tempUser.setPassword(passwordEncoder.encode(dto.getPassword()));
                        tempUser.setFirstTimeLogin(false);
                        userRepository.save(tempUser);
                        System.out.println("tempuser:" + tempUser);
                        message = "Đổi mật khẩu thành công";
                    } else {
                        System.out.println(dbOtp.getCode());
                        System.out.println(dto.getOtp());
                        message = "Mã otp không đúng vui lòng thử lại";
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            message = "Đã sảy ra lỗi trong quá trình đổi mật khẩu vui lòng thử lại sau.";
        }
        MessageDto messageDto = new MessageDto();
        messageDto.setObj(obj);
        messageDto.setMessage(message);
        return messageDto;
    }

    @Override
    public String activeAccount(String otp, Long userId) {
        try {
            Optional<User> dbUser = userRepository.findById(userId);
            if (dbUser.isPresent()) {
                User user = dbUser.get();
                OTP dbOtp = otpRepository.findByUser(user);
                if (dbOtp.getCode().equals(otp)) {
                    user.setActive(true);
                    userRepository.save(user);
                    return "Kích hoạt tài khoản thành công " +
                            "url:http://localhost:4200/auth";
                }
            } else {
                return "Kích hoạt tài khoản thất bại" +
                        "url:http://localhost:4200/auth";
            }
        } catch (Exception e) {

        }
        return "Kích hoạt tài khoản thất bại";
    }

    @Override
    public Boolean signupJe(UserDTO dto) {
        if (!userRepository.findOneByEmail(dto.getEmail()).isPresent() && !userRepository.findByUserName(dto.getUserName()).isPresent() && !userRepository.findByPhoneNumber(dto.getPhoneNumber()).isPresent()) {
            try {
                Set<Role> roles = roleRepository.findByCode(Constants.Role.JE);
                User user = userMapper.toEntity(dto);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setActive(false);
                user.setRoles(roles);
                user.setFirstTimeLogin(false);
                userRepository.save(user);
                OTP otp = new OTP(user);
                otpRepository.save(otp);
                String link = emailService.buildActiveEmail(user.getName(), otp.getCode(), user.getId());
                emailService.send(user.getEmail(), link);
                return true;
            } catch (Exception e) {
                log.error("cannot save to database");
                return false;
            }
        } else return false;
    }

}
