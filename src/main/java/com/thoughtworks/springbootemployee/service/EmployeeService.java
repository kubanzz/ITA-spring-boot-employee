package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Dto.EmployeeDto;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Page<Employee> getEmployees(Pageable pageable);

    Employee getEmployee(int employeeId);

    void addEmployee(EmployeeDto employeeDto);

    void updateEmployee(int employeeId,EmployeeDto employeeDto);

    void deleteEmployee(int id);

    List<Employee> getEmployeeByGender(String gender);
}
