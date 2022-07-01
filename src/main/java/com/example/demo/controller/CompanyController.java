package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.exceptionHandler.NotFoundException;
import com.example.demo.model.Company;
import com.example.demo.service.CompanyService;

@Controller
public class CompanyController {

	@Autowired
    private CompanyService companyService;
	
	// display list of companies
    @GetMapping("/companies")
    public String viewCompanyPage(Model model) {
        model.addAttribute("listCompanies", companyService.getAllCompany());
        return "companies";
    }
    @GetMapping("/showNewCompanyForm")
    public String showNewCompanyForm(Model model) {
        // create model attribute to bind form data
    	Company company = new Company();
        model.addAttribute("company", company);
        return "new_company";
    }
    
    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("company") Company company) {
        // save company to database
        companyService.saveCompany(company);
        return "redirect:/companies";
    }
    
    @GetMapping("/showFormForCompanyUpdate/{id}")
    public String showFormForCompanyUpdate(@PathVariable(value = "id") long id, Model model) throws NotFoundException {

        // get company from the service
        Company company = companyService.getCompanyById(id);

        model.addAttribute("company", company);
        return "update_company";
    }

    @GetMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable(value = "id") long id) {

        // call delete employee method 
        this.companyService.deleteCompanyById(id);
        return "redirect:/companies";
    }
}
