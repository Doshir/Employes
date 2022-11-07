package com.makarov.testProject.Service;

import com.makarov.testProject.entity.Department;
import com.makarov.testProject.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private  DepartmentRepo departmentRepo;

    @Autowired
    public DepartmentService(DepartmentRepo departmentRepo){
        this.departmentRepo=departmentRepo;

    }

    public Department findById(Long id){
        Optional<Department> optional = departmentRepo.findById(id);
        return optional.orElseThrow(IllegalStateException::new);
    }

    public Department save (Department department){
        Department department1 = new Department();
        department.setDepartmentName(department.getDepartmentName());
        return departmentRepo.save(department);
    }
    public String remove (long id ){
        Optional<Department> optional = departmentRepo.findById(id);
        Department department = optional.orElseThrow(IllegalStateException::new);
        departmentRepo.delete(department);

        return "Department удален успешно";
    }
    public Department update (Long id,Department department){
        Optional<Department> optional = departmentRepo.findById(id);
        Department department1= optional.orElseThrow(IllegalStateException::new);
        department1.setDepartmentName(department.getDepartmentName());

        return departmentRepo.save(department1);
    }
    public List<Department> findAll(List<Department> departments){
        Iterable<Department> optional = departmentRepo.findAll();
        return (List<Department>) optional;
     }
}
