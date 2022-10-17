package com.javainuse;

import com.javainuse.dto.DepartmentDto;
import com.javainuse.dto.EmployeeDto;
import com.javainuse.model.Department;
import com.javainuse.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap( Employee.class, EmployeeDto.class )
                .addMappings( mapper -> {
                    mapper.map( src -> src.getEmployee_id(), EmployeeDto::setEmployee_id );
                    mapper.map( src -> src.getEmail(), EmployeeDto::setEmail );
                    mapper.map( src -> src.getFirst_name(), EmployeeDto::setFirst_name );
                    mapper.map( src -> src.getLast_name(), EmployeeDto::setLast_name );
                    mapper.map( src -> src.getManager_id(), EmployeeDto::setManager_id );
                    mapper.map( src -> src.getPhone_number(), EmployeeDto::setPhone_number );
                    mapper.map( src -> src.getSalary(), EmployeeDto::setSalary );
                    mapper.map( src -> src.getHire_date(), EmployeeDto::setHire_date );

                } );
        modelMapper.createTypeMap( EmployeeDto.class, Employee.class )
                .addMappings( mapper -> {
                    mapper.map( src -> src.getDepartments(), Employee::setDepartments );
                    mapper.map( src -> src.getEmployee_id(), Employee::setEmployee_id );
                    mapper.map( src -> src.getEmail(), Employee::setEmail );
                    mapper.map( src -> src.getFirst_name(), Employee::setFirst_name );
                    mapper.map( src -> src.getLast_name(), Employee::setLast_name );
                    mapper.map( src -> src.getManager_id(), Employee::setManager_id );
                    mapper.map( src -> src.getPhone_number(), Employee::setPhone_number );
                    mapper.map( src -> src.getSalary(), Employee::setSalary );
                    mapper.map( src -> src.getHire_date(), Employee::setHire_date );

                } );

        modelMapper.createTypeMap( Department.class, DepartmentDto.class )
                .addMappings( mapper -> {
                    mapper.map( src -> src.getDepartment_id(), DepartmentDto::setDepartment_id );
                    mapper.map( src -> src.getManager_id(), DepartmentDto::setManager_id );
                    mapper.map( src -> src.getEmployees(), DepartmentDto::setEmployees );
                    mapper.map( src -> src.getDepartment_name(), DepartmentDto::setDepartment_name );

                } );

        modelMapper.createTypeMap( DepartmentDto.class, Department.class )
                .addMappings( mapper -> {
                    mapper.map( src -> src.getDepartment_id(), Department::setDepartment_id );
                    mapper.map( src -> src.getManager_id(), Department::setManager_id );
                    mapper.map( src -> src.getDepartment_name(), Department::setDepartment_name );

                } );
        return modelMapper;
    }

}
