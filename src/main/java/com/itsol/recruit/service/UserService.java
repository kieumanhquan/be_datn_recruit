package com.itsol.recruit.service;

import com.itsol.recruit.dto.JobPaginationDto;
import com.itsol.recruit.dto.MessageDto;
import com.itsol.recruit.dto.UserPaginationDto;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.web.vm.SearchJobVM;
import com.itsol.recruit.web.vm.SearchUserVM;

import java.util.List;

public interface UserService {

    public List<User> getAllUser();

    public User findById(Long id);

    public User findUserByUserName(String userName);

    User findByEmail(String email);

    List<User> findUserByRole(String role);

    MessageDto updateUser(User user);

    UserPaginationDto find(SearchUserVM searchUserVM, int pageNumber, int pageSize);
}
