package com.makarov.testProject.repository;

import com.makarov.testProject.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
}
