package com.itsol.recruit.security;


import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepositoryJpa;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            User user = userRepositoryJpa.findByUserName(username);
            if (user !=null ){
                return  createSpringSecurityUser(username, user);
            } else {
                return null;
            }
        }catch (Exception e){
            log.error("username is not exist!");
            return  null;
        }
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String userName, User user) {
        if (!user.isActive()) {
            throw new UserNotActivatedException("User " + userName + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream().map(authority -> new SimpleGrantedAuthority(authority.getRoleCode())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }

}
