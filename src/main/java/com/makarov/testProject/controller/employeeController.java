package com.makarov.testProject.controller;

import com.makarov.testProject.Service.EmployeeService;
import com.makarov.testProject.dto.EmployeeDto;
import com.makarov.testProject.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Table;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("employee")
public class employeeController {
    private final EmployeeService employeeService;
    @Autowired
    public employeeController(EmployeeService employeeService){
        this.employeeService=employeeService;

    }
    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable("id") @Valid long id){
        return employeeService.findById(id);
    }
    @GetMapping("/findAll")
    public List<EmployeeDto> findAll () {
      return employeeService.findAllEmployee();
    }
    @GetMapping("/firstAndLastName")
    public EmployeeDto employee(@RequestParam @Valid String firstName, @RequestParam @Valid String lastName){
           return employeeService.employee(firstName,lastName);
    }

   @PostMapping()
    public String employeeSave(@RequestBody EmployeeDto employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("{id}")
    public String employeeDeleted(@PathVariable("id") @Valid long id )
    {
        return employeeService.removeEmployee(id);

    }
    @PutMapping("{id}")
    public EmployeeDto employeeUpdate(@PathVariable("id") @Valid long id,@RequestBody @Valid EmployeeDto employee){
        return employeeService.employeeUpdate(id,employee);

   }

}
