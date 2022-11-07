package com.makarov.testProject.Service;

import com.makarov.testProject.dto.EmployeeDto;
import com.makarov.testProject.entity.Department;
import com.makarov.testProject.entity.Employee;
import com.makarov.testProject.repository.DepartmentRepo;
import com.makarov.testProject.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepo employeeRepo;
    private DepartmentRepo departmentRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo,DepartmentRepo departmentRepo){
        this.employeeRepo = employeeRepo;
        this.departmentRepo=departmentRepo;
    }

    public String save( EmployeeDto employeeDto) {;
        boolean isEmployeeAdult = isEmployeeAdult(employeeDto);
        if(!isEmployeeAdult){
            return "dont Saved";
        }
        Employee employee = new Employee();
        Department department = departmentRepo.findById(employeeDto.getDepartmentId()).orElseThrow();
        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setDepartment(department);
        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setGender(employeeDto.getGender());
        employee.setJobTittle(employeeDto.getJobTittle());
        employee.setLastName(employeeDto.getLastName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employeeRepo.save(employee);
        return "Saved";


    }
    public boolean isEmployeeAdult(EmployeeDto employee){
        LocalDate date = LocalDate.now();
        LocalDate localDateEmployee = LocalDate.of(employee.getDateOfBirth().getYear(),employee.getDateOfBirth().getMonthValue(),employee.getDateOfBirth().getDayOfMonth());
        long years =  Period.between(localDateEmployee,date).getYears();
        if(years<18){
            return false;
        }
        else {

            return true;
        }
    }

    public String removeEmployee(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        Employee employee1 = employee.orElse(null);
        if(employee1==null){
            return "Пользователь не найден";
        }
        else {
            employeeRepo.delete(employee.get());
            return "Deleted";
        }
    }

    public EmployeeDto findById(long id){
       Employee employee = employeeRepo.findById(id).orElseThrow(()->new IllegalStateException("Mistake"));
        EmployeeDto employeeDto =new EmployeeDto();
        employeeDto.setGender(employee.getGender());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setDepartmentId(employee.getDepartment().getDepartmentId());
        employeeDto.setJobTittle(employee.getJobTittle());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setFirstName(employee.getFirstName());
        return employeeDto;

    }

    public List<EmployeeDto> findAllEmployee () {
        List<Employee> employees = employeeRepo.findAll();
        List<EmployeeDto> employeeDto = new ArrayList<>();
        for(int i = 0;i<employees.size();i++){
            EmployeeDto employeeDto1 = new EmployeeDto();
            Employee employee = employees.get(i);
            employeeDto1.setEmployeeId(employee.getEmployeeId());
            employeeDto1.setGender(employee.getGender());
            employeeDto1.setFirstName(employee.getFirstName());
            employeeDto1.setLastName(employee.getLastName());
            employeeDto1.setJobTittle(employee.getJobTittle());
            employeeDto1.setDateOfBirth(employee.getDateOfBirth());
            employeeDto1.setDepartmentId(employee.getDepartment().getDepartmentId());
            employeeDto.add(employeeDto1);
        }
        return employeeDto;

    }
    public EmployeeDto employee( String firstName,  String lastName){
        Employee employee = employeeRepo.findByFirstNameAndLastName(firstName,lastName);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setGender(employee.getGender());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setJobTittle(employee.getJobTittle());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setDepartmentId(employee.getDepartment().getDepartmentId());
        return employeeDto;
    }
    public EmployeeDto employeeUpdate( long id, EmployeeDto employeeDto){
        boolean isEmployeeAdult = isEmployeeAdult(employeeDto);
        if(!isEmployeeAdult){
            throw new IllegalStateException("User not 18 years!");
        }
        Optional<Employee> employee1 = employeeRepo.findById(id);
        Employee employeeUpdate = employee1.orElseThrow(() -> new IllegalStateException("Mistake"));
        Department department1 = departmentRepo.findById(employeeDto.getDepartmentId()).orElseThrow(()->new IllegalStateException("Mistake"));
        employeeUpdate.setDateOfBirth(employeeDto.getDateOfBirth());
        employeeUpdate.setEmployeeId(employeeDto.getEmployeeId());
        employeeUpdate.setDepartment(department1);
        employeeUpdate.setGender(employeeDto.getGender());
        employeeUpdate.setJobTittle(employeeDto.getJobTittle());
        employeeUpdate.setLastName(employeeDto.getLastName());
        employeeUpdate.setFirstName(employeeDto.getFirstName());
        employeeRepo.save(employeeUpdate);
        return employeeDto;
    }

}
