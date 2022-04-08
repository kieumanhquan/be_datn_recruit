package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.JobPaginationDto;
import com.itsol.recruit.dto.MessageDto;
import com.itsol.recruit.dto.UserAndProfilesDto;
import com.itsol.recruit.dto.UserPaginationDto;
import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.ProfilesRepository;
import com.itsol.recruit.repository.RoleRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.UserService;
import com.itsol.recruit.web.vm.SearchJobVM;
import com.itsol.recruit.web.vm.SearchUserVM;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public final RoleRepository roleRepository;

    public final ProfilesRepository profilesRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,ProfilesRepository profilesRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.profilesRepository=profilesRepository;
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

    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public MessageDto updateUser(User user) {
        MessageDto message=new MessageDto();
        if(userRepository.findByUserName(user.getUserName()).isPresent()){
            User userDb=userRepository.findByUserName(user.getUserName()).get();
            user.setPassword(userDb.getPassword());
            user.setUserName(userDb.getUserName());
            user.setId(userDb.getId());
            userRepository.save(user);
            message.setMessage("Sửa thông tin người dùng thành công");
            message.setObj(true);
        }else{
            message.setMessage("Sửa thông tin người dùng thất bại");
            message.setObj(false);
        }
        return message;
    }


    public  UserPaginationDto  find(SearchUserVM searchUserVM, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        UserPaginationDto userPaginationDto = new UserPaginationDto();
        userPaginationDto.setList(userRepository.findBySearchUserVm("%" + searchUserVM.getUserName().toLowerCase() + "%",
                "%"+searchUserVM.getPhoneNumber()+"%","%"+ searchUserVM.getEmail() +"%",
                pageable).stream().collect(Collectors.toList()));
        userPaginationDto.setTotalPage((long) userRepository.findBySearchUserVm("%" + searchUserVM.getUserName().toLowerCase() + "%",
                "%"+searchUserVM.getPhoneNumber()+"%","%"+ searchUserVM.getEmail() +"%",
                pageable).getTotalPages());
        return userPaginationDto;
    }

    @Override
    public UserAndProfilesDto findUserProfilesByUserName(String userName) {
        UserAndProfilesDto userAndProfilesDto=new UserAndProfilesDto();
       User user= userRepository.findByUserName(userName).get();
        userAndProfilesDto.setUser(user);
       if(profilesRepository.findOneByUser(user)==null){
           Profiles profile= new Profiles();
           profile.setUser(user);
           userAndProfilesDto.setProfiles(profile);
       }else{
           userAndProfilesDto.setProfiles(profilesRepository.findOneByUser(user));
       }
        return userAndProfilesDto;
    }

    @Override
    public MessageDto updateUserProfiles(UserAndProfilesDto userAndProfilesDto) {
        MessageDto message=new MessageDto();
        try{

            userRepository.save(userAndProfilesDto.getUser());
            profilesRepository.save(userAndProfilesDto.getProfiles());
            message.setObj(true);
            message.setMessage("Cập nhật thành công");
        }catch(Exception e){
            message.setObj(false);
            message.setMessage("Cập nhật thất bại");
        }
        return message;
    }
}
