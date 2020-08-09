package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exceptions.company.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CompanyTest {

    @MockBean
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @Test
    void should_return_companies_when_getCompanies_unpage_given_none(){
        //given
        Page<Company> companies = new PageImpl<Company>(getMockCompanies());
        Mockito.when(companyRepository.findAll(Pageable.unpaged()))
                .thenReturn(companies);

        //when
        Page<Company> companiesQuired = companyService.getCompanies(Pageable.unpaged());

        //then
        assertEquals(10,companies.getNumberOfElements());
    }


    @Test
    void should_return_2_employees_when_get_employees_by_company_id_given_company_id_1_and_2_employees_in_this_company() {
        //given
        int companyId = 1;

        Mockito.when(companyRepository.findById(companyId)).thenReturn(java.util.Optional.of(getMockCompany()));

        //when
        List<Employee> employeesQueried = companyService.getEmployeesByCompanyId(companyId);

        //then
        assertEquals(10, employeesQueried.size());
    }

    @Test
    void should_return_company_name_oocl_when_get_company_by_id_given_company_id_1() {
        //given
        int companyId = 1;
        Mockito.when(companyRepository.findById(companyId)).thenReturn(java.util.Optional.of(getMockCompany()));

        //when
        Company company = companyService.getCompany(companyId);

        //then
        Assertions.assertEquals("oocl",company.getName());
    }

    @Test
    void should_return_1_company_when_add_company_given_1_company(){
        //given
        Company company = getMockCompany();
        Mockito.when(companyRepository.save(any(Company.class))).thenReturn(any());

        //when
        companyService.addCompany(company);

        //then
        Mockito.verify(companyRepository).save(company);
    }

    @Test
    void should_save_when_update_company_name_given_new_company_info(){
        //given
        Company company = getMockCompany();
        company.setName("tw");
        Mockito.when(companyRepository.save(any(Company.class))).thenReturn(company);

        //when
        companyService.updateCompany(company);

        //then
        Mockito.verify(companyRepository).save(company);
    }

    @Test
    void should_delete_when_delete_company_by_company_id_given_company_id() {
        //given
        int companyId = 1;

        //when
        companyService.deleteCompany(companyId);

        //then
        Mockito.verify(companyRepository,times(1)).deleteById(companyId);
    }

    private List<Company> getMockCompanies() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "xiaoyi", 18, "Male"));
        employees.add(new Employee(2, "xiaoer", 18, "Male"));
        employees.add(new Employee(3, "xiaosan", 19, "Male"));
        employees.add(new Employee(4, "xiaosi", 19, "Male"));
        employees.add(new Employee(5, "xiaowu", 20, "Male"));
        employees.add(new Employee(6, "xiaoliu", 20, "Female"));
        employees.add(new Employee(7, "xiaoqi", 21, "Female"));
        employees.add(new Employee(8, "xiaoba", 21, "Female"));
        employees.add(new Employee(9, "xiaojiu", 18, "Male"));
        employees.add(new Employee(10, "xiaoshi", 18, "Male"));
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "Mm", employees));
        companies.add(new Company(2, "Aa", employees));
        companies.add(new Company(3, "Bb", employees));
        companies.add(new Company(4, "Cc", employees));
        companies.add(new Company(5, "Dd", employees));
        companies.add(new Company(6, "Ff", employees));
        companies.add(new Company(7, "Gg", employees));
        companies.add(new Company(8, "Ll", employees));
        companies.add(new Company(9, "Pp", employees));
        companies.add(new Company(10, "Uu",employees));
        return companies;
    }

    private Company getMockCompany() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "xiaoyi", 18, "Male"));
        employees.add(new Employee(2, "xiaoer", 18, "Male"));
        employees.add(new Employee(3, "xiaosan", 19, "Male"));
        employees.add(new Employee(4, "xiaosi", 19, "Male"));
        employees.add(new Employee(5, "xiaowu", 20, "Male"));
        employees.add(new Employee(6, "xiaoliu", 20, "Female"));
        employees.add(new Employee(7, "xiaoqi", 21, "Female"));
        employees.add(new Employee(8, "xiaoba", 21, "Female"));
        employees.add(new Employee(9, "xiaojiu", 18, "Male"));
        employees.add(new Employee(10, "xiaoshi", 18, "Male"));
        return new Company(1, "oocl", employees);
    }
}
