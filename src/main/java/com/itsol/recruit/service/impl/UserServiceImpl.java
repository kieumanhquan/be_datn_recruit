package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getAllUser() {
        return userRepository.getAllUser();
    }
}
