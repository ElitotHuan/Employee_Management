package com.web_service.employee_management.features.job;

import com.web_service.employee_management.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

	@Autowired
	private JobRepository repository;

	public Object getAllJob() {
		return repository.getJobs();
	}

	public Object addJob(Job job) {
		if (repository.existsByTitle(job.getTitle())) {
			return new Response.Error("Job title already existed", 409);
		} else {
			repository.save(job);
			return new Response.Success("Job has been added sucessfully");
		}
	}
}
