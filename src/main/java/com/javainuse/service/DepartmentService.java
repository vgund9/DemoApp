package com.javainuse.service;


import com.javainuse.dto.DepartmentDto;
import com.javainuse.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    DepartmentDto save(DepartmentDto department);

    DepartmentDto findById(Integer id);

    void deleteById(Integer id);

    Page<Department> findAll(Pageable paging);

    void deleteAll();
}

