package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.exceptionHandler.NotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.service.CompanyService;
import com.example.demo.service.EmployeeService;



@Controller
public class EmployeeController {
	
	 	@Autowired
	    private EmployeeService employeeService;
	 	@Autowired
	 	private CompanyService companyService;

	    // display list of employees
	    @GetMapping("/employees")
	    public String viewEmployeePage(Model model) {
	        model.addAttribute("listEmployees", employeeService.getAllEmployees());
	        return "employees";
	    }
	    
	    @GetMapping("/showNewEmployeeForm")
	    public String showNewEmployeeForm(Model model) {
	        // create model attribute to bind form data
	        Employee employee = new Employee();
	        model.addAttribute("employee", employee);
	        model.addAttribute("listCompanies", companyService.getAllCompany());
	        return "new_employee";
	    }
	    
	    @PostMapping("/saveEmployee")
	    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
	        // save employee to database
	        employeeService.saveEmployee(employee);
	        return "redirect:/employees";
	    }
	    
	    @GetMapping("/showFormForUpdate/{id}")
	    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) throws NotFoundException {

	        // get employee from the service
	        Employee employee = employeeService.getEmployeeById(id);

	        model.addAttribute("listCompanies", companyService.getAllCompany());
	        
	        // set employee as a model attribute to pre-populate the form
	        model.addAttribute("employee", employee);
	        return "update_employee";
	    }

	    @GetMapping("/deleteEmployee/{id}")
	    public String deleteEmployee(@PathVariable(value = "id") long id) {

	        // call delete employee method 
	        this.employeeService.deleteEmployeeById(id);
	        return "redirect:/employees";
	    }

}
