package com.itsol.recruit.repository;

import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.repoext.UserRepositoryExt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryExt {
    public User findUserById(long id);
}
