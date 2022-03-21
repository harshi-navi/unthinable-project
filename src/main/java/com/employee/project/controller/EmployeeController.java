package com.employee.project.controller;

import java.util.List;

import com.employee.project.model.Employee;
import com.employee.project.model.Response;
import com.employee.project.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "/v1/employee")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value="/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response create(@RequestBody Employee employee){
        try{
            Employee savedEmployee = employeeService.createEmployee(employee);
            Response response = new Response("success", "employee successfully created");
            response.setData(savedEmployee);
            return response;
        } catch(Exception e){
            return new Response("error", e.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(@RequestBody Employee employee){
        try{
            Employee savedEmployee = employeeService.updateEmployee(employee);
            Response response = new Response("success", "employee data successfully updated");
            response.setData(savedEmployee);
            return response;
        } catch(Exception e){
            return new Response("error", e.getMessage());
        }
    }

    @RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
    public Response fetch(@PathVariable Integer id) throws Exception {
        try{
            Employee employee = employeeService.fetchEmployee(id);
            Response response = new Response("success", "employee fetched successfully");
            response.setData(employee);
            return response;
        } catch(Exception e){
            return new Response("error", e.getMessage());
        }
    }

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public Response fetchAll(){
        try{
            List<Employee> employees = employeeService.fetchAllEmployees();
            Response response = new Response("success", "all employees fetched successfully");
            response.setData(employees);
            return response;
        } catch(Exception e){
            return new Response("error", e.getMessage());
        }
    }
}