package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Job;

import java.util.List;

public interface JobRepository {

    List<Job> find(String name, int numberExperience, int salaryMin, int salaryMax, int pageNumber, int pageSize);

    Boolean add(Job job);

    boolean update(Job job);

    Job findOneById(Long id);
}
