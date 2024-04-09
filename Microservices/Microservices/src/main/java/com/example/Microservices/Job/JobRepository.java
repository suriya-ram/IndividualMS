package com.example.Microservices.Job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, Long>
{

}
