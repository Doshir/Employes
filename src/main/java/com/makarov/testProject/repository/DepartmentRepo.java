package com.makarov.testProject.repository;

import com.makarov.testProject.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepo extends CrudRepository<Department, Long> {
    void findAllBy();
}
