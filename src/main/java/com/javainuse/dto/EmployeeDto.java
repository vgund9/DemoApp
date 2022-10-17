package com.javainuse.dto;

import com.javainuse.model.Department;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class EmployeeDto {

    Integer employee_id;

    @NotBlank(message = "The first_name is required.")
    @NotEmpty(message = "The first name is required.")
    @Size(min = 2, max = 50, message = "The length of first name must be between 2 and 50 characters.")
    String first_name;

    @NotEmpty(message = "The last name is required.")
    @Size(min = 2, max = 50, message = "The length of last name must be between 2 and 50 characters.")
    String last_name;

    @Email(message = "Valid email required")
    String email;
    @Pattern(regexp = "^\\d{10}$")
    String phone_number;


    @NotNull(message = "The date of birth is required.")
    Date hire_date;


    @Min(value = 1, message = "Salary should be grater than zero")
    @NotNull(message = "Salary should be grater than zero")
    Double salary;
    Integer manager_id;
    List<Department> departments;

}
