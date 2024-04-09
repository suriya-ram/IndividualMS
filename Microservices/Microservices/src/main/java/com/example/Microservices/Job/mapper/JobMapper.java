package com.example.Microservices.Job.mapper;

import com.example.Microservices.Job.JobEntity;
import com.example.Microservices.Job.dto.JobDTOs;
import com.example.Microservices.Job.external.Company;
import com.example.Microservices.Job.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDTOs mapToJobWithCompanyDTO(JobEntity job, Company company,
                                                 List<Review>reviews) {
        JobDTOs jobwithcompanyDTO = new JobDTOs();

        jobwithcompanyDTO.setId(job.getId());
        jobwithcompanyDTO.setDescription(job.getDescription());
        jobwithcompanyDTO.setLocation(job.getLocation());
        jobwithcompanyDTO.setMinSalary(job.getMinSalary());
        jobwithcompanyDTO.setMaxSalary(job.getMaxSalary());
        jobwithcompanyDTO.setTitle(job.getTitle());
        jobwithcompanyDTO.setCompany(company);

        jobwithcompanyDTO.setReviews(reviews);

        return jobwithcompanyDTO;
    }

}
