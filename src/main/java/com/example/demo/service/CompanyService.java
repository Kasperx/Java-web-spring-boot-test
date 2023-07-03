package com.example.demo.service;

import java.util.List;

import com.example.demo.exceptionHandler.NotFoundException;
import com.example.demo.model.Company;


public interface CompanyService {

	List < Company > getAllCompany();
    void saveCompany(Company company);
    Company getCompanyById(long id) throws NotFoundException;
    void deleteCompanyById(long id);
//    void createFirstData();
}
