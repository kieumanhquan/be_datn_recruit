package com.itsol.recruit.repository;

import com.itsol.recruit.entity.JobRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRegisterRepository extends JpaRepository<JobRegister, Long> {
    @Query(value = "select u from job_register u where u.user.name like :name and u.statusJobRegister.id = :status_id order by u.dateRegister DESC ")
    Page<JobRegister> find(@Param("name") String name, @Param("status_id") Long statusId,
                  Pageable pageable);

    @Query(value = "select u from job_register u where u.user.name like :name and u.statusJobRegister.id = :status_id order by u.user.name DESC ")
    Page<JobRegister> sortByName(@Param("name") String name, @Param("status_id") Long statusId,
                           Pageable pageable);

    JobRegister findOneById(Long id);

    @Query(value = "select u from job_register u where u.job.id = :id")
    List<JobRegister> findByJobId(@Param("id") Long id);
}
