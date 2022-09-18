package com.makarov.testProject.Service;

import com.makarov.testProject.entity.Employee;
import com.makarov.testProject.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
///При удалении проверить существует ли пользователь.
//Если существует - удалить его и вернуть сообщение '' пользователь XXX успешно удалён ''.
//Если не существует - вернуть сообщение '' пользователь XXX не найден ''
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;


    public String employeeNotEight(Employee employee) {
        LocalDate date = LocalDate.now();
        LocalDate localDateEmployee = LocalDate.of(employee.getDateOfBirth().getYear(),employee.getDateOfBirth().getMonthValue(),employee.getDateOfBirth().getDayOfMonth());
       long years =  Period.between(localDateEmployee,date).getYears();
       if(years<18){
           return "Пользователю нет 18-ти лет";
       }
       else {
           return "Пользователь успешно сохранен";
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
            return "Пользователь успешно удален";
        }
    }
}
