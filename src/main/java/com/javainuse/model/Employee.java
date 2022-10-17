package com.javainuse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer employee_id;
    @NotEmpty
    @Size(min = 2, message = "employee first name should have at least 2 characters")

    String first_name;
    @NotEmpty
    @Size(min = 2, message = "employee first name should have at least 2 characters")
    String last_name;

    @Email
    String email;

    @Pattern(regexp = "^\\d{10}$")
    String phone_number;

    @NotNull
    Date hire_date;

    @NotNull
    @Min( value = 1 , message = "Salary should be grater than zero")
    Double salary;
    Integer manager_id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "employee_department",
            joinColumns = {
                    @JoinColumn(name = "employee_employee_id", referencedColumnName = "employee_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "department_department_id", referencedColumnName = "department_id",
                            nullable = false, updatable = false)})
    private Set<Department> departments = new HashSet<>();

}
