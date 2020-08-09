package com.thoughtworks.springbootemployee.service.Impl;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exceptions.company.CompanyAddException;
import com.thoughtworks.springbootemployee.exceptions.company.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Page<Company> getCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public List<Employee> getEmployeesByCompanyId(int companyId) {
        return companyRepository
                .findById(companyId).orElseThrow(CompanyNotFoundException::new)
                .getEmployee();
    }

    @Override
    public Company getCompany(int companyId) {
        return companyRepository
                .findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    public void addCompany(Company company) {
        try {
            companyRepository.save(company);
        } catch (Exception e) {
            throw new CompanyAddException();
        }
    }

    @Override
    public void updateCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(int companyId) {
        companyRepository.deleteById(companyId);
    }
}
