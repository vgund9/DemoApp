package com.javainuse.dto;

import com.javainuse.model.Employee;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;


@Data
public class DepartmentDto {

    Integer department_id;

    @NotBlank(message = "The department name is required.")
    String department_name;

    Integer manager_id;

    Set<Employee> employees;
}
