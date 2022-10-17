package com.javainuse.service;

import com.javainuse.dto.EmployeeDto;
import com.javainuse.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    EmployeeDto save(EmployeeDto employee);

    EmployeeDto findById(Integer id);

    Page<Employee> findAll(Pageable paging);

    void deleteById(Integer id);

    void deleteAll();
}
