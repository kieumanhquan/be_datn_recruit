package com.itsol.recruit.repository;

import com.itsol.recruit.entity.JobRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRegisterRepository extends JpaRepository<JobRegister, Long> {

    JobRegister findOneById(Long id);

    @Query(value = "select u from job_register u where u.job.id = :id")
    List<JobRegister> findByJobId(@Param("id") Long id);
}
