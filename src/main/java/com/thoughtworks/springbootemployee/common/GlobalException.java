package com.thoughtworks.springbootemployee.common;

import com.thoughtworks.springbootemployee.exceptions.company.CompanyAddException;
import com.thoughtworks.springbootemployee.exceptions.company.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exceptions.employee.EmployeeNoFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void companyNotFound() {
    }

    @ExceptionHandler(CompanyAddException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void companyInfoError() {
    }

    @ExceptionHandler(EmployeeNoFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void employeeNotFound() {

    }
}
