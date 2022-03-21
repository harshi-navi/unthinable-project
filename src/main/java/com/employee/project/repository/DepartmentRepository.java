package com.employee.project.repository;

import com.employee.project.model.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    
    public Department getDepartmentById(Integer id);

    public Department getDepartmentByName(String name);
}
