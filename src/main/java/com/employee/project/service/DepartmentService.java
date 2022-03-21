package com.employee.project.service;

import java.util.List;

import com.employee.project.repository.DepartmentRepository;
import com.employee.project.model.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) throws Exception{
        int id = department.getId();
        String deptName = department.getName();
        if(departmentRepository.getDepartmentById(id) == null && departmentRepository.getDepartmentByName(deptName) == null){
            return departmentRepository.save(department);
        }
        throw new Exception("department already exists");
    }

    public Department updateDepartment(Department department) throws Exception{
        int id = department.getId();
        Department departmentFromDb = departmentRepository.getDepartmentById(id);
        if(departmentFromDb != null) {
            if(department.getName() != null){
                departmentFromDb.setName(department.getName());
            }
            return departmentRepository.save(departmentFromDb);
        }
        throw new Exception("department doesn't exists");

    }

    public Department fetchDepartment(int id) throws Exception{
        Department department = departmentRepository.getDepartmentById(id);
        if(department != null) return department;
        throw new Exception("department id doesn't exist");
    }

    public List<Department> fetchAllDepartments(int page, int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Department> departmentsInPages = departmentRepository.findAll(pageableRequest);
        return departmentsInPages.getContent();
    }

    public boolean isDepartmentExistsForId(int id) {
        Department department = departmentRepository.getDepartmentById(id);
        return department != null;
    }
}
