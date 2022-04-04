package com.itsol.recruit.repository;

import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.repoext.UserRepositoryExt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryExt {

    Optional<User> findByUserName(String userName);

    Optional<User> findOneByEmail(String username);

    User findOneById(Long contactId);

    Optional<User> findByPhoneNumber(String phoneNumber);

}