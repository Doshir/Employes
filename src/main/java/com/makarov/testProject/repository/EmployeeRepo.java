package com.makarov.testProject.repository;

import com.makarov.testProject.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
     List<Employee> findAll();
     Employee findByFirstNameAndLastName(String firstName,String lastName);
}
