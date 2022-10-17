package com.javainuse.controller;

import com.javainuse.dto.DepartmentDto;
import com.javainuse.dto.EmployeeDto;
import com.javainuse.model.Employee;
import com.javainuse.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto Employee) {
        try {
            EmployeeDto response = employeeService.save( Employee );
            return new ResponseEntity<>( response, HttpStatus.CREATED );
        } catch (Exception e) {
            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") Integer id) {
        EmployeeDto employee = employeeService.findById( id );

        if (employee != null) {
            return new ResponseEntity<>( employee, HttpStatus.OK );
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEmployees(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of( page, size );
            Page<Employee> employeeDtos = employeeService.findAll( paging );

            if (employeeDtos.isEmpty()) {
                return new ResponseEntity<>( HttpStatus.NO_CONTENT );
            }
            Type listType = new TypeToken<List<EmployeeDto>>() {
            }.getType();
            List<EmployeeDto> employees = modelMapper.map( employeeDtos.getContent(), listType );
            Map<String, Object> response = new HashMap<>();
            response.put( "employees", employees );
            response.put( "currentPage", employeeDtos.getNumber() );
            response.put( "totalItems", employeeDtos.getTotalElements() );
            response.put( "totalPages", employeeDtos.getTotalPages() );

            return new ResponseEntity<>( response, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Integer id, @Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto response = employeeService.findById( id );

        if (null != response) {
            response.setFirst_name( employeeDto.getFirst_name() );
            response.setLast_name( employeeDto.getLast_name() );
            response.setDepartments( employeeDto.getDepartments() );
            response.setManager_id( employeeDto.getManager_id() );
            response.setSalary( employeeDto.getSalary() );
            response.setPhone_number( employeeDto.getPhone_number() );
            response.setHire_date( employeeDto.getHire_date() );
            response = employeeService.save( response );
            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Integer id) {
        try {
            employeeService.deleteById( id );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        try {
            employeeService.deleteAll();
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }


}
