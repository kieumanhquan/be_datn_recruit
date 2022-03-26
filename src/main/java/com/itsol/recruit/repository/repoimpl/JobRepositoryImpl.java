package com.itsol.recruit.repository.repoimpl;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class JobRepositoryImpl implements JobRepository{
    @Override
    public List<Job> find(String name, int numberExperience, int salaryMin, int salaryMax, int pageNumber, int pageSize) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            NativeQuery<Job> query = session.createNativeQuery("SELECT * FROM" +
                    "(" +
                    "    SELECT a.*, rownum r__" +
                    "    FROM" +
                    "    (" +
                    "        SELECT * FROM Job WHERE lower(name) like lower(to_char(concat(concat('%', :p_name), '%')))" +
                    "                       and NUMBER_EXPERIENCE = :p_number_experience" +
                    "                       and JOB.SALARY_MIN <= :p_salary_min" +
                    "                       and JOB.SALARY_MAX <= :p_salary_max" +
                    "                       and JOB.ACADEMIC_LEVEL_ID <= :p_academic_level" +
                    "        ORDER BY CREATE_DATE,NAME" +
                    "    ) a" +
                    "    WHERE rownum < ((:pageNumber * :pageSize) + 1 )" +
                    ")" +
                    "WHERE r__ >= (((:pageNumber-1) * :pageSize) + 1)", Job.class);
            query.setParameter("p_student_name", name);
            query.setParameter("p_number_experience",numberExperience);
            query.setParameter("p_salary_min",salaryMin);
            query.setParameter("p_salary_max",salaryMax);
            query.setParameter("pageNumber", pageNumber);
            query.setParameter("pageSize", pageSize);
            return query.getResultList();
        } catch (Exception e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean add(Job job) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(job);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            log.error(String.valueOf(e));
        }
        return false;
    }

    @Override
    public boolean update(Job job) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(job);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            log.error(String.valueOf(e));
        }
        return false;
    }

    @Override
    public Job findOneById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Job job = session.load(Job.class,id);
            session.getTransaction().commit();
            return job;
        } catch (HibernateException e) {
            e.printStackTrace();
            log.error(String.valueOf(e));
        }
        return null;
    }
}

