package com.web_service.employee_management.module.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    boolean existsByTitle(String title);

    @Query(value = "select new com.web_service.employee_management.module.job.JobDTO(j.job_Id , j.title , j.salary_min , j.salary_max) from Job j")
    List<JobDTO> getJobs();
}
