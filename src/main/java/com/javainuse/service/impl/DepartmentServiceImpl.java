package com.javainuse.service.impl;


import com.javainuse.dto.DepartmentDto;
import com.javainuse.model.Department;
import com.javainuse.repository.DepartmentRepository;
import com.javainuse.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service

public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public DepartmentDto save(DepartmentDto departmentDto) {
        Department department= new Department();
        BeanUtils.copyProperties( departmentDto, department );
        department =  departmentRepository.save( department );
        BeanUtils.copyProperties( department,departmentDto );
        return departmentDto;
    }

    @Override
    public DepartmentDto findById(Integer id) {
        Optional<Department> department=  departmentRepository.findById( id );
        DepartmentDto departmentDto = new DepartmentDto();
        if(department.isPresent()) {
            modelMapper.map( department.get(), departmentDto );
            return departmentDto;
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Integer employeeId) {
        departmentRepository.deleteById( employeeId );
    }

    @Override
    public Page<Department> findAll(Pageable paging) {
      return departmentRepository.findAll(paging);
    }

    @Override
    @Transactional
    public void deleteAll() {
        departmentRepository.deleteAll();
    }
}
