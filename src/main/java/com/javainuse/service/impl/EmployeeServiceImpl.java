package com.javainuse.service.impl;

import com.javainuse.dto.EmployeeDto;
import com.javainuse.model.Department;
import com.javainuse.model.Employee;
import com.javainuse.repository.DepartmentRepository;
import com.javainuse.repository.EmployeeRepository;
import com.javainuse.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties( employeeDto, employee );
        if (!CollectionUtils.isEmpty( employeeDto.getDepartments() )) {
            List<Department> department = departmentRepository.findAllById( employeeDto.getDepartments().stream()
                    .map( s -> s.getDepartment_id() ).collect( Collectors.toList() ) );
            if (!CollectionUtils.isEmpty( department )) {
                Set<Department> set = new HashSet<>();
                set.addAll( department );
                employee.setDepartments( set );
            }
        }
        Employee employee1 = employeeRepository.save( employee );
        BeanUtils.copyProperties( employee1, employeeDto );
        return employeeDto;
    }

    @Override
    public EmployeeDto findById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById( id );
        EmployeeDto employeeDto = new EmployeeDto();
        if (employee.isPresent()) {
            modelMapper.map( employee.get(), employeeDto );
            return employeeDto;
        }
        return null;
    }

    @Override
    public Page<Employee> findAll(Pageable paging) {
        Page<Employee> employees = employeeRepository.findAll(paging);
        return employees;
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById( id );
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
