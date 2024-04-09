package com.example.Microservices.Job;

//import java.util.ArrayList;

import com.example.Microservices.Job.dto.JobDTOs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@RequestMapping("jobz")
public class JobController {

	
	private JobService jobservice;
	
	public JobController(JobService jobservice) {
		super();
		this.jobservice = jobservice;
	}

	@GetMapping("jobz")
	public List<JobDTOs> findAll(){
		return jobservice.findAll();
		
	}
	
	@PostMapping("/jobz")
	
		public String createJob(@RequestBody JobEntity job) {
		jobservice.CreateJob(job);

		return "jobs added successfully";

	}
	@GetMapping("jobz/{id}")
	 public ResponseEntity<JobDTOs> getJobByid(@PathVariable Long id) {
		JobDTOs jobwithcompanyDTO =jobservice.getJobByid(id);
	  if(jobwithcompanyDTO !=null)
		  return  new ResponseEntity<> (jobwithcompanyDTO,HttpStatus.OK);
	  return  new ResponseEntity<> (HttpStatus.NOT_FOUND);
	  
	 }
	
	@DeleteMapping("jobz/{id}")
	public ResponseEntity<String>DeleteJob(@PathVariable Long id)
	{
		boolean deleted = jobservice.deleteJobById(id);
		if( deleted)
		{
			return new ResponseEntity<>("job deleted Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("there is no job",HttpStatus.NOT_FOUND);
	}
	
	//@PutMapping("jobz/{id}")
	
	@RequestMapping(value = "jobz/{id}", method=RequestMethod.PUT)
	
	public ResponseEntity<String>updateJob  (@PathVariable Long id, @RequestBody JobEntity updatedjob)
	{
		boolean updated = jobservice.updateJobById(id,updatedjob );
		if(updated)
			return new ResponseEntity<>("job updated successfully",HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
