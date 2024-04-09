package com.example.Microservices.Job.clients;


import com.example.Microservices.Job.external.Company;
import com.example.Microservices.Job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name ="REVIEW-SERVICE")
public interface ReviewClient {



    @GetMapping("/reviews")
    List<Review> getReviews(@RequestParam("companyId") Long CompanyId);
}
