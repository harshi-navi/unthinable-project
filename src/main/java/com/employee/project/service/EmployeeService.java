package com.employee.project.service;

import java.util.List;

import com.employee.project.repository.EmployeeRepository;
import com.employee.project.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    public Employee createEmployee(Employee employee) throws Exception{
        int id = employee.getId();
        int deptId = employee.getDepartmentId();
        if(!departmentService.isDepartmentExistsForId(deptId)){
            throw new Exception("department id is not valid");
        }
        if(employeeRepository.getEmployeeById(id) == null){
            return employeeRepository.save(employee);
        }
        throw new Exception("employee already exists");
    }

    public Employee updateEmployee(Employee employee) throws Exception{
        int id = employee.getId();
        Employee employeeFromDb = employeeRepository.getEmployeeById(id);
        if(employeeFromDb != null){
            if(employee.getName() != null){
                employeeFromDb.setName(employee.getName());
            }
            if(employee.getSalary() != null){
                employeeFromDb.setSalary(employee.getSalary());
            }
            if(employee.getDepartmentId() != null){
                if(!departmentService.isDepartmentExistsForId(employee.getDepartmentId())){
                    throw new Exception("department id is not valid");
                }
                employeeFromDb.setDepartmentId(employee.getDepartmentId());
            }
            if(employee.getDob() != null){
                employeeFromDb.setDob(employee.getDob());
            }
            return employeeRepository.save(employeeFromDb);
        }
        throw new Exception("employee doesn't exists");

    }

    public Employee fetchEmployee(Integer id) throws Exception{
        Employee employee = employeeRepository.getEmployeeById(id);
        if(employee != null) return employee;
        throw new Exception("employee id doesn't exist");
    }

    public List<Employee> fetchAllEmployees() {
        return employeeRepository.findAll();
    }
}
