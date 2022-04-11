package com.itsol.recruit.security;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.AuthenticateRepository;
import com.itsol.recruit.repository.OtpRepository;
import com.itsol.recruit.repository.RoleRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.OtpService;
import com.itsol.recruit.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import java.util.Set;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    OtpRepository otpRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticateRepository authenticateRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    OtpService otpService;

    @Autowired
    EmailService emailService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
//       Role role=new Role();
//        role.setId(1L);
//        role.setCode(Constants.Role.ADMIN);
//        roleRepository.save(role);
//        role.setId(2L);
//        role.setCode(Constants.Role.JE);
//        roleRepository.save(role);
//        role.setId(3L);
//        role.setCode(Constants.Role.USER);
//        roleRepository.save(role);
//
//        if (alreadySetup) {
//            return;
//        }
//        if (!userRepository.findByUserName("admin").isPresent() ) {
//            Set<Role> adminRole = roleRepository.findByCode(Constants.Role.ADMIN);
//            User user = new User();
//            user.setUserName("admin");
//            user.setName("admin");
//            user.setEmail("zexaldai@gmail.com");
//            user.setPhoneNumber("0961130568");
//            user.setBirthday(new Date(2001 - 03 - 24));
//            user.setPassword(passwordEncoder.encode("admin"));
//            user.setRoles(adminRole);
//            user.setActive(true);
//            user.setDelete(false);
//            userRepository.save(user);
//            OTP otp=new OTP(user);
//            otpRepository.save(otp);
//            String email=emailService.buildOtpEmail(user.getName(),otp.getCode());
//            String link=emailService.buildActiveEmail(user.getName(),otp.getCode(),user.getId());
//            System.out.println("TEST OTP: "+otp.getCode());
//            System.out.println("Admin: " + user.toString());
//            emailService.send(user.getEmail(),email);
//            emailService.send(user.getEmail(),link);
//            alreadySetup = true;
//        }
    }
}