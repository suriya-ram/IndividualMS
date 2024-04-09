package com.example.companyms.Company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import com.suriya.SpringProjectFirst.Review.Review;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	
	
	private CompanyService companyService;
	
	
	
	
	
	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}





	@GetMapping
	public List<Company>getAllCompanies(){
		return companyService.getAllCompanies();
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company){
		companyService.updateCompanies(company, id);
		return  new ResponseEntity<> ("Company updated Successfully",HttpStatus.OK);
		
	}
	
	@PostMapping()
	public ResponseEntity<String>  CreateCompany(@RequestBody Company company){
		companyService.createCompany(company);
		return new ResponseEntity<> ("company created successfully",HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>  DeleteCompany(@PathVariable Long id)
	{boolean isDeleted = companyService.deleteCompanyById(id);
	
	   if(isDeleted)
	{
		return new ResponseEntity<>("company deleted successfully",HttpStatus.OK);
	}
	   else
	   {
		   return new  ResponseEntity<>("company not found",HttpStatus.NOT_FOUND);
	   }
	
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Company>getCompany(@PathVariable Long id) {
		Company company = companyService.getCompanyById(id);
		if(company != null)
		{ return new ResponseEntity<>(company,HttpStatus.OK);
	     }
		else
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}}