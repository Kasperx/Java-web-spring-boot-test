package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptionHandler.NotFoundException;
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;
import com.github.javafaker.Faker;


@Service
public class CompanyServiceImpl extends Tools implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	

	@Override
	public List<Company> getAllCompany() {
		return companyRepository.findAll();
	}

	@Override
	public void saveCompany(Company company) {
		this.companyRepository.save(company);
		
	}

	@Override
	public Company getCompanyById(long id) throws NotFoundException {
        return companyRepository.findById(id).orElseThrow(() -> new NotFoundException("Company not found for id ::"+id)) ;
	}

	@Override
	public void deleteCompanyById(long id) {
		this.companyRepository.deleteById(id);
	}

//	@Override
//	public void createFirstData() {
//
//		Company company = new Company();
//		company.setCompanyName(faker.name().lastName());
//		this.companyRepository.save(company);
//	}
}
