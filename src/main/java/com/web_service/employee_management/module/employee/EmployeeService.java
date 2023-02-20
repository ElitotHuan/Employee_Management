package com.web_service.employee_management.module.employee;

import com.web_service.employee_management.module.department.Department;
import com.web_service.employee_management.module.department.DepartmentRepository;
import com.web_service.employee_management.module.password.Password;
import com.web_service.employee_management.module.salary.Salary;
import com.web_service.employee_management.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmployeeService {
    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Object getAll(Long id) {
        if (id == null) {
            return repository.getEmployees();
        } else {
            Employee employee = repository.findById(id).get();
            return new EmployeeDTO(employee.getId(), employee.getFullName(), employee.getBirthDay(),
                    employee.getEmail(), employee.getPhoneNumber(), employee.getHiredDate(), employee.getJob().getTitle() , employee.getSalary().getSalary()    );
        }
    }

    public Object addEmployee(Employee employeeInfo) {
        logger.info("Initialize adding employee function....");
        if (repository.existsByEmail(employeeInfo.getEmail())) {
            return new Response.Error("Email already existed", 400);
        } else {
            Employee newEmployee = new Employee(employeeInfo.getFullName(), employeeInfo.getBirthDay(), employeeInfo.getEmail()
                    , employeeInfo.getPhoneNumber(), LocalDate.now());
            Department d = departmentRepository.findById(employeeInfo.getDepartment().getId()).get();

            //Set information
            newEmployee.setSalary(new Salary(newEmployee , employeeInfo.getSalary().getSalary(),
                    LocalDate.now() , LocalDate.now().plusYears(3)));
            newEmployee.setPassword(new Password(newEmployee , employeeInfo.getPassword().getPassword(),
                    LocalDate.now().plusYears(3) , null));
            newEmployee.setJob(employeeInfo.getJob());
            newEmployee.setDepartment(d);

            //Update department employees
            d.setNumberOfEmployees(d.getNumberOfEmployees() + 1);

            //Save to database
            repository.save(newEmployee);
            departmentRepository.save(d);
            return new Response.Success("Employee has been added sucessfully");
        }
    }


    public Object updateEmployee(Long id , Employee updateInfo) {
        if (repository.existsByEmail(updateInfo.getEmail())) {
            return new Response.Error("Email already existed", 400);
        } else {
            Employee original = repository.getReferenceById(id);

            if (original.getDepartment().getId() != updateInfo.getDepartment().getId()) {
                original = new Employee(updateInfo.getFullName(), updateInfo.getBirthDay(), updateInfo.getEmail()
                        , updateInfo.getPhoneNumber(), updateInfo.getHiredDate());
                Department newDepartment = updateInfo.getDepartment();
                Department oldDepartment = original.getDepartment();

                original.setSalary(updateInfo.getSalary());
                original.setJob(updateInfo.getJob());
                original.setDepartment(newDepartment);

                newDepartment.setNumberOfEmployees(newDepartment.getNumberOfEmployees() + 1);
                oldDepartment.setNumberOfEmployees(oldDepartment.getNumberOfEmployees() - 1);

                repository.save(original);
                departmentRepository.save(newDepartment);
                departmentRepository.save(oldDepartment);
                return new Response.Success("Employee information has been updated sucessfully");
            }

            original = new Employee(updateInfo.getFullName(), updateInfo.getBirthDay(), updateInfo.getEmail()
                    , updateInfo.getPhoneNumber(), updateInfo.getHiredDate());
            original.setSalary(updateInfo.getSalary());
            original.setJob(updateInfo.getJob());
            original.setDepartment(updateInfo.getDepartment());
            repository.save(original);
            return new Response.Success("Employee information has been updated sucessfully");
        }
    }

    public Object deleteEmployee(Long id) {
        Employee e = repository.getReferenceById(id);
        Department d = departmentRepository.findById(e.getDepartment().getId()).get();
        d.setNumberOfEmployees(d.getNumberOfEmployees() - 1);

        repository.delete(e);
        departmentRepository.save(d);
        return new Response.Success("Remove sucessfully");
    }

}
