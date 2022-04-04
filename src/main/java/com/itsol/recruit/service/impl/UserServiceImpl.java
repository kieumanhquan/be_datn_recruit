package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.RoleRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName).get();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findOneByEmail(email).orElse(null);
    }

    @Override
    public List<User> findUserByRole(String role) {
       List<User> users = userRepository.findAll();
       List<User> userList = new ArrayList<>();
       for(User u: users){
           for (Role role1: u.getRoles()){
               if (role1.getCode().equals(role)){
                   userList.add(u);
                   break;
               }
           }
       }
        return userList;
    }


}
