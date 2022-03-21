package com.employee.project.controller;

import java.util.List;

import com.employee.project.constants.Constants;
import com.employee.project.model.Employee;
import com.employee.project.model.Response;
import com.employee.project.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/v1/employee")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value="/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response create(@RequestBody Employee employee){
        try{
            Employee savedEmployee = employeeService.createEmployee(employee);
            Response response = new Response(Constants.SUCCESS, "employee successfully created");
            response.setData(savedEmployee);
            return response;
        } catch(Exception e){
            return new Response(Constants.ERROR, e.getMessage());
        }
    }

    @PostMapping(value = "/update")
    public Response update(@RequestBody Employee employee){
        try{
            Employee savedEmployee = employeeService.updateEmployee(employee);
            Response response = new Response(Constants.SUCCESS, "employee data successfully updated");
            response.setData(savedEmployee);
            return response;
        } catch(Exception e){
            return new Response(Constants.ERROR, e.getMessage());
        }
    }

    @GetMapping(value = "/fetch/{id}")
    public Response fetch(@PathVariable Integer id) throws Exception {
        try{
            Employee employee = employeeService.fetchEmployee(id);
            Response response = new Response(Constants.SUCCESS, "employee fetched successfully");
            response.setData(employee);
            return response;
        } catch(Exception e){
            return new Response(Constants.ERROR, e.getMessage());
        }
    }

    @GetMapping(value = "/fetch")
    public Response fetchAll(@RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "limit", defaultValue = "10") int limit){
        try{
            List<Employee> employees = employeeService.fetchAllEmployees(page, limit);
            Response response = new Response(Constants.SUCCESS, "all employees fetched successfully");
            response.setData(employees);
            return response;
        } catch(Exception e){
            return new Response(Constants.ERROR, e.getMessage());
        }
    }
}