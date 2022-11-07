package com.makarov.testProject.controller;

import com.makarov.testProject.Service.DepartmentService;
import com.makarov.testProject.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("department")
public class DepartmentController {
    private final DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping({"id"})
    public Department findById (@PathVariable Long id) {
        return departmentService.findById(id);
    }

    @PostMapping
    public Department save(@RequestBody Department department){
        return departmentService.save(department);
    }

    @DeleteMapping({"id"})
    public String remove(@PathVariable Long id ){
        return departmentService.remove(id);
    }

    @PutMapping({"id"})
    public Department update(@PathVariable Long id, @RequestBody Department department){
        return departmentService.update(id,department);
    }
    @GetMapping
    public List<Department> findAll(@RequestBody List<Department> departmentsList){

       return departmentService.findAll(departmentsList);
    }




}
