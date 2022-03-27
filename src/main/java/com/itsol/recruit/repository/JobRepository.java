package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Job findOneById(Long id);

    @Query(value = "select * from job  where NAME like :name and NUMBER_EXPERIENCE = :numberExperience and SALARY_MIN >= :salaryMin and SALARY_MAX <= :salaryMax \n-- #pageable\n",
            countQuery = "select count (*) from job  where NAME like :name and NUMBER_EXPERIENCE = :numberExperience and SALARY_MIN >= :salaryMin and SALARY_MAX <= :salaryMax",nativeQuery = true)
    Page<Job> find(@Param("name") String name, @Param("numberExperience") Integer numberExperience,
                   @Param("salaryMin") Integer salaryMin, @Param("salaryMax") Integer salaryMax, Pageable pageable);

//    @Query(value = "SELECT * FROM job where name = ?1 and NUMBER_EXPERIENCE = ?2 " , nativeQuery = true)
//    List<Job> findTest(String name,Integer numberExperience,
//                   Integer salaryMin, Integer salaryMax);

}
