package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "/companies")
    public Page<Company> getCompanies(@PageableDefault() Pageable pageable, boolean unpaged) {
        if (unpaged) {
            companyService.getCompanies(Pageable.unpaged());
        }
        return companyService.getCompanies(pageable);
    }

    @GetMapping("/companies/{companyId}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int companyId) {
        return companyService.getEmployeesByCompanyId(companyId);
    }

    @GetMapping("/companies/{companyId}")
    public Company getCompanyByCompanyId(@PathVariable Integer companyId) {
        return companyService.getCompany(companyId);
    }

    @PostMapping("/companies")
    public void addCompany(@RequestBody Company company){
        companyService.addCompany(company);
    }

    @PutMapping("/companies")
    public void updateCompany(@RequestBody Company company){
        companyService.updateCompany(company);
    }

    @DeleteMapping("/companies/{companyId}")
    public void deleteCompany(@PathVariable Integer companyId){
        companyService.deleteCompany(companyId);
    }
}
