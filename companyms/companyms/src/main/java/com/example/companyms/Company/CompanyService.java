package com.example.companyms.Company;

import java.util.List;

public interface CompanyService {

	
	
	List<Company>getAllCompanies();
	
	boolean updateCompanies(Company company ,Long id);
	
	void createCompany(Company company);
	
	boolean deleteCompanyById(Long id);

	Company getCompanyById(Long id);

	//Company getCompanyById(Long companyId);
}
