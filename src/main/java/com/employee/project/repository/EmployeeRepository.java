package com.employee.project.repository;


import com.employee.project.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
 
    public Employee getEmployeeById(Integer id);
}
