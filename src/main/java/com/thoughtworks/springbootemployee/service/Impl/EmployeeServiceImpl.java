package com.thoughtworks.springbootemployee.service.Impl;

import com.thoughtworks.springbootemployee.Dto.EmployeeDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exceptions.employee.EmployeeNoFoundException;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Autowired
    private CompanyService companyService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Page<Employee> getEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee getEmployee(int employeeId) {
        return employeeRepository
                .findById(employeeId)
                .orElseThrow(EmployeeNoFoundException::new);
    }

    @Override
    public void addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getName(), employeeDto.getAge(), employeeDto.getGender());
        Company company = companyService.getCompany(employeeDto.getCompanyId());
        employee.setCompany(company);
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(int employeeId, EmployeeDto employeeDto) {
        Employee employee = this.getEmployee(employeeId);
        employee.setName(employeeDto.getName());
        employee.setAge(employeeDto.getAge());
        employee.setGender(employeeDto.getGender());
        Company company = companyService.getCompany(employeeDto.getCompanyId());
        employee.setCompany(company);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository
                .findByGender(gender);
    }

}
