package com.employee.project.controller;

import java.util.List;

import com.employee.project.constants.Constants;
import com.employee.project.model.Department;
import com.employee.project.model.Response;
import com.employee.project.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/v1/department")
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping(value="/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response create(@RequestBody Department department){
        try{
            Department savedDepartment = departmentService.createDepartment(department);
            Response response = new Response(Constants.SUCCESS, "department successfully created");
            response.setData(savedDepartment);
            return response;
        } catch(Exception e){
            return new Response(Constants.ERROR, e.getMessage());
        }
    }

    @PostMapping(value = "/update")
    public Response update(@RequestBody Department department){
        try{
            Department savedDepartment = departmentService.updateDepartment(department);
            Response response = new Response(Constants.SUCCESS, "department data successfully updated");
            response.setData(savedDepartment);
            return response;
        } catch(Exception e){
            return new Response(Constants.ERROR, e.getMessage());
        }
    }

    @GetMapping(value = "/fetch/{id}")
    public Response fetch(@PathVariable Integer id){
        try{
            Department department = departmentService.fetchDepartment(id);
            Response response = new Response(Constants.SUCCESS, "department fetched successfully");
            response.setData(department);            
            return response;
        } catch(Exception e){
            return new Response(Constants.ERROR, e.getMessage());
        }
    }

    @GetMapping(value = "fetch")
    public Response fetchAll(@RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "limit", defaultValue = "10") int limit){
        try{
            List<Department> departments = departmentService.fetchAllDepartments(page, limit);
            Response response = new Response(Constants.SUCCESS, "all departments fetched successfully");
            response.setData(departments);
            return response;
        } catch(Exception e){
            return new Response(Constants.ERROR, e.getMessage());
        }
    }
}