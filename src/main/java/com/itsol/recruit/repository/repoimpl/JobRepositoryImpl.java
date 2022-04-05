package com.itsol.recruit.repository.repoimpl;

import com.itsol.recruit.dto.JobPaginationDto;
import com.itsol.recruit.entity.*;
import com.itsol.recruit.repository.*;
import com.itsol.recruit.repository.repoext.JobRepositoryExt;
import com.itsol.recruit.utils.SqlReader;
import com.itsol.recruit.web.vm.SearchJobVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class JobRepositoryImpl extends BaseRepository implements JobRepositoryExt {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AcademicLevelRepository academicLevelRepository;

    @Autowired
    private JobPositionRepository jobPositionRepository;

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private StatusJobRepository statusJobRepository;

    @Autowired
    private WorkingFormRepository workingFormRepository;

    @Override
    public JobPaginationDto search(SearchJobVM searchJobVM, Integer pageNumber, Integer pageSize) {
        try{
            String query = SqlReader.getSqlQueryById(SqlReader.ADMIN_MODULE_JOB, "searchJob");
            Map<String, Object> parameters = new HashMap<>();
            if (!ObjectUtils.isEmpty(searchJobVM.getName())) {
                String jobName=searchJobVM.getName().toUpperCase();
                query += " and UPPER(job.name) like :p_job_name";
                parameters.put("p_job_name","%"+jobName+"%");
            }
            if (!ObjectUtils.isEmpty(searchJobVM.getStatusId())) {
                query += " and job.status_id = :p_status_id";
                parameters.put("p_status_id",searchJobVM.getStatusId());
            }
            if (!ObjectUtils.isEmpty(searchJobVM.getSalaryMax())) {
                query += " and job.salary_max < :p_salary_max";
                parameters.put("p_salary_max",searchJobVM.getSalaryMax());
            }
            if (!ObjectUtils.isEmpty(searchJobVM.getSalaryMin())) {
                query += " and job.salary_min > :p_salary_min";
                parameters.put("p_salary_min",searchJobVM.getSalaryMin());
            }

            Integer p_startrow;
            Integer p_endrow;
            if(pageNumber==0)
            {
                p_startrow=0;
                p_endrow=p_startrow+pageSize;
            }
            else {
                p_startrow=pageSize*pageNumber+1;
                p_endrow=p_startrow+pageSize-1;
            }

            query += " order by job.create_date DESC ),count_all as( select count (*) total from tempselect )," +
                    " paging as( select * from tempselect  where ROWNR between :p_startrow and :p_endrow " +
                    ") select p.*, c.total from paging p, count_all c ";
            parameters.put("p_startrow", p_startrow);
            parameters.put("p_endrow", p_endrow);
            JobPaginationDto jobPaginationDto = new JobPaginationDto();
            if(getNamedParameterJdbcTemplate().query(query, parameters, new JobMapper()).isEmpty()){
                jobPaginationDto.setList(new ArrayList<>());
                jobPaginationDto.setTotalPage(0L);
            }
            else {
                jobPaginationDto.setList(getNamedParameterJdbcTemplate().query(query, parameters, new JobMapper()));
                jobPaginationDto.setTotalPage(getNamedParameterJdbcTemplate().query(query, parameters, new totalMap()).get(0));
            }

            return jobPaginationDto;
        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    class JobMapper implements RowMapper<Job> {

        @Override
        public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
            Job dto = new Job();
            dto.setName(rs.getString("name"));
            dto.setCreateDate(rs.getDate("create_date"));
            dto.setNumberExperience(rs.getInt("number_experience"));
            dto.setAddressWork(rs.getString("address_work"));
            dto.setDescription(rs.getString("description"));
            dto.setDueDate(rs.getDate("due_date"));
            dto.setStartRecruitmentDate(rs.getDate("start_recruitment_date"));
            dto.setBenefits(rs.getString("benefits"));
            dto.setSalaryMax(rs.getInt("salary_max"));
            dto.setSalaryMin(rs.getInt("salary_min"));
            dto.setSkills(rs.getString("skills"));
            dto.setQtyPerson(rs.getInt("qty_person"));
            dto.setViews(rs.getInt("views"));
            dto.setId(rs.getLong("id"));

            User user = new User();
            user.setId(rs.getLong("create_id"));
            dto.setCreator(userRepository.findById(user.getId()).get());

            AcademicLevel academicLevel = new AcademicLevel();
            academicLevel.setId(rs.getLong("academic_level_id"));
            AcademicLevel newAcademicLevel = academicLevelRepository.findById(academicLevel.getId()).get();
            dto.setAcademicLevel(newAcademicLevel);

            User userContact = new User();
            userContact.setId(rs.getLong("contact_id"));
            User newContact = userRepository.findById(userContact.getId()).get();
            dto.setContact(newContact);

            JobPosition jobPosition = new JobPosition();
            jobPosition.setId(rs.getLong("job_position_id"));
            JobPosition newJobPosition = jobPositionRepository.findById(jobPosition.getId()).get();
            dto.setJobPosition(newJobPosition);

            Rank levelRank = new Rank();
            levelRank.setId(rs.getLong("rank_id"));
            Rank newLevelRank = rankRepository.findById(levelRank.getId()).get();
            dto.setRank(newLevelRank);

            WorkingForm workingForm = new WorkingForm();
            workingForm.setId(rs.getLong("working_form_id"));
            WorkingForm newWorkingForm = workingFormRepository.findById(workingForm.getId()).get();
            dto.setWorkingForm(newWorkingForm);

            StatusJob statusJob = new StatusJob();
            statusJob.setId(rs.getLong("status_id"));
            StatusJob newStatusJob = statusJobRepository.findById(statusJob.getId()).get();
            dto.setStatusJob(newStatusJob);

            return dto;
        }
    }

    class totalMap implements RowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
            long total = rs.getLong("total");
            return total;
        }
    }
}
