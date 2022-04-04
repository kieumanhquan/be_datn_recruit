package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Job findOneById(Long id);

    @Query(value = "select u from job u " +
            "where u.name like :name " +
            "and u.statusJob.id = :status_id " +
            "and u.salaryMin >= :salaryMin " +
            "and u.salaryMax <= :salaryMax " +
            "order by u.createDate DESC ")
    Page<Job> find(@Param("name") String name, @Param("status_id") Long statusId,
                   @Param("salaryMin") Integer salaryMin, @Param("salaryMax") Integer salaryMax, Pageable pageable);

    @Query(value = "select u from job u " +
            "where u.name like :name and u.statusJob.id = :status_id " +
            "and u.salaryMin >= :salaryMin and u.salaryMax <= :salaryMax " +
            "order by u.name DESC ")
    Page<Job> sortByName(@Param("name") String name, @Param("status_id") Long statusId,
                         @Param("salaryMin") Integer salaryMin, @Param("salaryMax") Integer salaryMax, Pageable pageable);

    @Query(value = "select u from job u " +
            "where u.createDate > :number_date and u.statusJob.id = 2 " +
            "order by u.createDate desc")
    Page<Job> getJobNew(@Param("number_date") Date numberDate, Pageable pageable);

    @Query(value = "select u from job u " +
            "where u.salaryMax > :salary and u.statusJob.id = 2 " +
            "order by u.createDate desc")
    Page<Job> getJobHighSalary(@Param("salary") Integer salary, Pageable pageable);

    @Query(value ="select JOB.*,temp.countJob as datuyen from JOB inner join " +
            "(select JOB.id, count(JOB.id) as countJob from JOB  " +
            "inner join job_register on JOB.id  = job_register.job_id " +
            "where JOB.STATUS_ID = 2 " +
            "group by JOB.id ) temp on temp.id = JOB.id where temp.countJob < JOB.qty_person  and JOB.due_date <= :number_date", nativeQuery = true)
    Page<Job> getJobDue(@Param("number_date") Date numberDate, Pageable pageable);
}
