package com.example.Microservices.Job;

import com.example.Microservices.Job.dto.JobDTOs;

import java.util.List;

public interface JobService {
	
	
	
	List<JobDTOs>findAll();
	void CreateJob(JobEntity job);
	JobDTOs getJobByid(Long id);
	boolean deleteJobById(Long id);
	boolean updateJobById(Long id, JobEntity updatedjob);

}
