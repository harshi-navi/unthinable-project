package com.employee.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.employee.project.model.Department;
import com.employee.project.model.Employee;


public class TestDataProvider {
    
    public static Department getValidDepartmentData(){
        Department department = new Department();
        department.setId(1);
        department.setName("finance");
        return department;
    }

    public static List<Department> getAllDepartmentsData(){
        List<Department> departments = new ArrayList<>();
        departments.add(getValidDepartmentData());
        return departments;
    }

    public static Employee getValidEmployeeData(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setDob(new Date());
        employee.setSalary(50000);
        employee.setName("Ajay kumar");
        employee.setDepartmentId(1);
        return employee;
    }

    public static List<Employee> getAllEmployeesData(){
        List<Employee> employees = new ArrayList<>();
        employees.add(getValidEmployeeData());
        return employees;
    }
}
