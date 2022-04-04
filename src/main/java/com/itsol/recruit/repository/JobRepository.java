package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Job findOneById(Long id);

    @Query(value = "select u from job u where u.name like :name and u.statusJob.id = :status_id and u.salaryMin >= :salaryMin and u.salaryMax <= :salaryMax order by u.createDate DESC ")
    Page<Job> find(@Param("name") String name, @Param("status_id") Long statusId,
                       @Param("salaryMin") Integer salaryMin, @Param("salaryMax") Integer salaryMax, Pageable pageable);


    @Query(value = "select u from job u where u.name like :name and u.statusJob.id = :status_id and u.salaryMin >= :salaryMin and u.salaryMax <= :salaryMax order by u.name DESC ")
    Page<Job> sortByName(@Param("name") String name, @Param("status_id") Long statusId,
                   @Param("salaryMin") Integer salaryMin, @Param("salaryMax") Integer salaryMax, Pageable pageable);


}
