package com.makarov.testProject.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

    private String jobTittle;

    private String gender;

    private LocalDate dateOfBirth;


}
