package com.example.companyms.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//import com.suriya.SpringProjectFirst.Job.JobEntity;



@Service
public class CompanyImpl implements CompanyService {

	
	@Autowired
	private CompanyRepository repo;
	@Override
	public List<Company> getAllCompanies() {
	
		return repo.findAll();
	}
	
	
	@Override
	public boolean updateCompanies(Company company, Long id) {
		
		Optional<Company>companyOptional = repo.findById(id);
		
		if(companyOptional.isPresent())
		{
			Company comp= companyOptional.get();
				
			comp.setName(company.getName());
			comp.setDescription(company.getDescription());

			//comp.setMinSalary(company.getMinSalary());
			
			
			repo.save(company);
			
		return true;
		}
		return false;
		}


	@Override
	public void createCompany(Company company) {
		repo.save(company);
		
	}


	@Override
	public boolean deleteCompanyById(Long id) {
		
		if(repo.existsById(id))
		{
			repo.deleteById(id);
			return true;
		}
		
		return false;
	}


	@Override
	public Company getCompanyById(Long id) {
		
		return repo.findById(id).orElse(null);
	}
	


}
