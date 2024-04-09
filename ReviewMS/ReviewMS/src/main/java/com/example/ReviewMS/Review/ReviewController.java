package com.example.ReviewMS.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	
	@Autowired
	private ReviewService reviewService;
	
	
	@GetMapping
	public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
     
		
		return new ResponseEntity<>(reviewService.getAllReviews(companyId),HttpStatus.OK);
	
	}
	
	
	@PostMapping
	public ResponseEntity<String> addReview (@RequestParam Long companyId,
			                                     @RequestBody Review review)
	{
   boolean isSaved = reviewService.addReview(companyId, review);
   if(isSaved)
    return new ResponseEntity<>("Reviews added successfully",HttpStatus.OK);
   else
	   return new ResponseEntity<>("Reviews not saved",HttpStatus.NOT_FOUND);
	
	}
	
	@GetMapping("/{reviewId}")
	
	public ResponseEntity<Review>  getReview(   @PathVariable Long companyId,
			                                      @PathVariable Long reviewId)
	{ return new ResponseEntity<>(reviewService.getReview(companyId,reviewId),HttpStatus.OK);
    }
	
	

	@PutMapping("/{reviewId}")
	
	public ResponseEntity<String>  updateReview(
			                                      @PathVariable Long reviewId,
			                                      @RequestBody Review review)
	
	   
	{ 
		 boolean isUpdated = reviewService.updateReview(reviewId,review);
		 if(isUpdated)
		return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
		 else
			 return new ResponseEntity<>("Review not found",HttpStatus.NOT_FOUND);
		
    }
	

	@DeleteMapping("/{reviewId}")
	
	public ResponseEntity<String>  deleteReview(
			                                      @PathVariable Long reviewId)
			                                      
	
	   
	{ 
		 boolean isDeleted = reviewService.deleteReview(reviewId);
		 if(isDeleted)
		return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
		 else
			 return new ResponseEntity<>("Review not deleted",HttpStatus.NOT_FOUND);
		
	}
}