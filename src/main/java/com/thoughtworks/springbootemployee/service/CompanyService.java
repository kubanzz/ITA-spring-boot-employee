package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    Page<Company> getCompanies(Pageable pageable);

    List<Employee> getEmployeesByCompanyId(int companyId);

    Company getCompany(int companyId);

    void addCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(int companyId);
}
