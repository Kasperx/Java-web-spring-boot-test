package com.example.demo.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptionHandler.NotFoundException;
import com.example.demo.model.Company;
import com.example.demo.model.Employee;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.EmployeeRepository;
import com.github.javafaker.Faker;

@Service
public class EmployeeServiceImpl extends Tools implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
 	private CompanyService companyService;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
		
	}

	@Override
	public Employee getEmployeeById(long id) throws NotFoundException {
		
        return employeeRepository.findById(id).orElseThrow(()-> new NotFoundException("Employee not found for id ::"+id));
	}

	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
		
	}
	public void createData() {
		
//		Faker faker = null;
//		faker = new Faker(new Locale("de-DE"));
		Employee employee = null;
		Faker temp = new Faker();
		Company company = new Company(
				"Roehrig",
				temp.name().firstName().substring(0,1)+"."+temp.name().lastName()+"@gmail.com"
				);
		companyService.saveCompany(company);
		
		for(int count=0; count<10; count++) {
		
			/*********/
			faker = new Faker();
			/*********/
			employee = new Employee();
			employee.setFirstName(faker.name().firstName());
			employee.setLastName(faker.name().lastName());
			employee.setEmail(faker.name().firstName().substring(0,1)+"."+faker.name().lastName()+"@gmail.com");
			employee.setCompany(company);
			System.out.println(employee.toString());
			this.employeeRepository.save(employee);
		}
	}
}
