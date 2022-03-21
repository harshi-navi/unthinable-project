package com.employee.project.controller;

import java.util.List;

import com.employee.project.model.Department;
import com.employee.project.model.Response;
import com.employee.project.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "/v1/department")
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value="/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response create(@RequestBody Department department){
        try{
            Department savedDepartment = departmentService.createDepartment(department);
            Response response = new Response("success", "department successfully created");
            response.setData(savedDepartment);
            return response;
        } catch(Exception e){
            return new Response("error", e.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(@RequestBody Department department){
        try{
            Department savedDepartment = departmentService.updateDepartment(department);
            Response response = new Response("success", "department data successfully updated");
            response.setData(savedDepartment);
            return response;
        } catch(Exception e){
            return new Response("error", e.getMessage());
        }
    }

    @RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
    public Response fetch(@PathVariable Integer id){
        try{
            Department department = departmentService.fetchDepartment(id);
            Response response = new Response("success", "department fetched successfully");
            response.setData(department);            
            return response;
        } catch(Exception e){
            return new Response("error", e.getMessage());
        }
    }

    @RequestMapping(value = "fetch", method = RequestMethod.GET)
    public Response fetchAll(){
        try{
            List<Department> departments = departmentService.fetchAllDepartments();
            Response response = new Response("success", "all departments fetched successfully");
            response.setData(departments);
            return response;
        } catch(Exception e){
            return new Response("error", e.getMessage());
        }
    }
}