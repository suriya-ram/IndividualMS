package com.example.Microservices.Job;

import com.example.Microservices.Job.clients.CompanyClient;
import com.example.Microservices.Job.clients.ReviewClient;
import com.example.Microservices.Job.dto.JobDTOs;
import com.example.Microservices.Job.external.Company;
import com.example.Microservices.Job.external.Review;
import com.example.Microservices.Job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class JobsImpl implements JobService{
	
	
	//private List<JobEntity>jobs = new ArrayList<>();
	//private Long nextID= 1L;
	JobRepository jobRepository;

	@Autowired
    private CompanyClient companyClient;
	@Autowired
	private ReviewClient reviewClient;


//	@Autowired
//	RestTemplate restTemplate;

	public JobsImpl(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}

	@Override
	public List<JobDTOs> findAll() {

		List<JobEntity> jobs = jobRepository.findAll();
		List<JobDTOs> jobwithCompanydtos = new ArrayList<>();

		//RestTemplate restTemplate = new RestTemplate();



		 return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
private JobDTOs convertToDTO(JobEntity job){

//	Company company=	restTemplate.getForObject("http://COMPANY-SERVICE:8086/companies/"+job.getCompanyId(),
//			Company.class);


	Company company = companyClient.getCompany(job.getCompanyId());
	List<Review> reviews =reviewClient.getReviews(job.getCompanyId());
//	ResponseEntity<List<Review>>reviewResponse =restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
//			HttpMethod.GET,
//			null,
//			new ParameterizedTypeReference<List<Review>>() {
//			});
// List<Review> reviews = reviewResponse.getBody();
	JobDTOs jobwithcompanyDTO = JobMapper.mapToJobWithCompanyDTO(job,company,reviews);
	//jobwithcompanyDTO.setCompany(company);

	return jobwithcompanyDTO;
}
	@Override
	public void CreateJob( JobEntity job) {
		
		//job.setId(nextID++);
		//jobs.add(job);
		
		jobRepository.save(job);
	}

	@Override
	public JobDTOs getJobByid(Long id) {

		JobEntity job =jobRepository.findById(id).orElse(null);
		return convertToDTO(job);
	}

	@Override
	public boolean deleteJobById(Long id) {


		try {
		jobRepository.deleteById(id);
		return true;}
		catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateJobById(Long id, JobEntity updatedjob) {
		

		
		Optional<JobEntity>Joboptional = jobRepository.findById(id);
	
		if(Joboptional.isPresent())
		{
			JobEntity job = Joboptional.get();
				
			job.setTitle(updatedjob.getTitle());
			job.setDescription(updatedjob.getDescription());
			job.setMinSalary(updatedjob.getMinSalary());
			job.setMaxSalary(updatedjob.getMaxSalary());
			job.setLocation(updatedjob.getLocation());
			
			jobRepository.save(job);
			
		return true;
		}
		return false;
		}
	}
	
