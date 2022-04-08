package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.MessageDto;
import com.itsol.recruit.dto.UserAndProfilesDto;
import com.itsol.recruit.dto.UserPaginationDto;
import com.itsol.recruit.entity.AcademicLevel;
import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.AcademicLevelService;
import com.itsol.recruit.service.ProfileService;
import com.itsol.recruit.service.UserService;
import com.itsol.recruit.web.vm.SearchUserVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class UserController {

   public final UserService userService ;

   public final ProfileService profileService;

   public final AcademicLevelService academicLevelService;

    public UserController(UserService userService, ProfileService profileService, AcademicLevelService academicLevelService) {
        this.userService = userService;
        this.profileService=profileService;
        this.academicLevelService=academicLevelService;
    }

    @PostMapping(value = "/user")
    public ResponseEntity<User> add(@RequestBody User user){
        return ResponseEntity.ok().body( userService.save(user));
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> getAllUser(){
        return  ResponseEntity.ok().body( userService.getAllUser());
    }

    @GetMapping(value = "/user/profiles/academicLevels")
    public ResponseEntity<List<AcademicLevel>> getAllAcademicLevel(){
        return  ResponseEntity.ok().body( academicLevelService.getAllAcademicLevel());
    }
    @GetMapping(value = "/user/id={id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        return  ResponseEntity.ok().body( userService.findById(id));
    }

    @GetMapping(value = "/user/profiles={id}")
    public ResponseEntity<Profiles> findProfilesByUserId(@PathVariable Long id){
        return  ResponseEntity.ok().body( profileService.getProfileByUserId(id));
    }

    @GetMapping(value = "/user/username={username}")
    public ResponseEntity<User> findUserByUserName(@PathVariable String username){
        return  ResponseEntity.ok().body( userService.findUserByUserName(username));
    }

    @GetMapping(value = "/user/profiles/userName={username}")
    public ResponseEntity<UserAndProfilesDto> findUserProfilesByUserName(@PathVariable String username){
        return  ResponseEntity.ok().body( userService.findUserProfilesByUserName(username));
    }

    @GetMapping(value = "/user/role={role}")
    public ResponseEntity<List<User>> findUserByURole(@PathVariable String role){
        return  ResponseEntity.ok().body( userService.findUserByRole(role));
    }

    @PutMapping(value="/user")
  public ResponseEntity<MessageDto> updateUser(@RequestBody User user){
    return ResponseEntity.ok().body(userService.updateUser(user));}

    @PostMapping(value="/user/profiles")
    public ResponseEntity<MessageDto> updateUserProfiles(@RequestBody UserAndProfilesDto userAndProfilesDto){
        return ResponseEntity.ok().body(userService.updateUserProfiles(userAndProfilesDto));}

    @PostMapping("user/searches")
    public UserPaginationDto find(@RequestBody SearchUserVM searchUserVM, @RequestParam(name = "page") int pageNumber,
                                  @RequestParam(name = "size") int pageSize) {
        return userService.find(searchUserVM, pageNumber, pageSize);
    }
}
