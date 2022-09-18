package com.makarov.testProject.controller;

import com.makarov.testProject.Service.EmployeeService;
import com.makarov.testProject.entity.Employee;
import com.makarov.testProject.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employee")
public class employeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    EmployeeRepo employeeRepo;
    @GetMapping("/{id}")
    public Employee getPoint(@PathVariable("id") long id){
        Optional<Employee> employee = employeeRepo.findById( id);
        return employee.orElseThrow(() -> new IllegalStateException("Ошибка")) ;



    }
    @GetMapping()
    public List<Employee> getEmploee () {
        Iterable<Employee> employee = employeeRepo.findAll();
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee1 : employee){
            employeeList.add(employee1);
        }
       return employeeList;
    }

   @PostMapping("/addEmployee")
    public String employeeSave(@RequestBody Employee employee){
        return employeeService.employeeNotEight(employee);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public String employeeDeleted(@PathVariable("id") long id )
    {
        return employeeService.removeEmployee(id);

    }
    @PutMapping("update/{id}")
    public Employee employee(@PathVariable("id") long id,@RequestBody Employee employee){
        Optional<Employee> employee1 = employeeRepo.findById(id);
        Employee employeeUpdate = employee1.orElseThrow(() -> new IllegalStateException("Ошибка обновления"));
        employeeUpdate.setFirstName(employee.getFirstName());
        employeeUpdate.setLastName(employee.getLastName());
        employeeUpdate.setDepartmentId(employee.getDepartmentId());
        employeeUpdate.setJobTittle(employee.getJobTittle());
        employeeUpdate.setGender(employee.getGender());
        employeeUpdate.setDateOfBirth(employee.getDateOfBirth());

        return  employeeRepo.save(employeeUpdate);

   }






}
