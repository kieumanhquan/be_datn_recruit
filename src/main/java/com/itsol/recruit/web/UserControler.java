package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.Api.Path.PREFIX)

public class UserControler {

   public final UserService userService ;

    public UserControler(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public User getAllUser(){
        return userService.getAllUser();
    }
}
