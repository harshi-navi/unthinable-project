package com.employee.project;

import java.util.Collections;
import java.util.List;

import com.employee.project.repository.EmployeeRepository;
import com.employee.project.model.Employee;
import com.employee.project.service.DepartmentService;
import com.employee.project.service.EmployeeService;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeServiceTest {
    
    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    DepartmentService departmentService;
    
    @InjectMocks
    EmployeeService employeeService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createEmployeeTest() throws Exception {
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(TestDataProvider.getValidEmployeeData());
        Mockito.when(departmentService.isDepartmentExistsForId(Mockito.anyInt())).thenReturn(true);
        Employee employee = employeeService.createEmployee(TestDataProvider.getValidEmployeeData());
        Assert.assertEquals(employee.getId(), Integer.valueOf(1));
    }

    @Test(expected = Exception.class)
    public void createEmployeeForAlreadyExistsEmployeeTest() throws Exception {
        Mockito.when(employeeRepository.getEmployeeById(Mockito.anyInt())).thenReturn(TestDataProvider.getValidEmployeeData());
        Employee employee = employeeService.createEmployee(TestDataProvider.getValidEmployeeData());
        Assert.assertEquals(employee.getId(), Integer.valueOf(2));
    }

    @Test(expected = Exception.class)
    public void createEmployeeForInvalidDepartmentTest() throws Exception {
        Mockito.when(departmentService.isDepartmentExistsForId(Mockito.anyInt())).thenReturn(false);
        Mockito.when(employeeRepository.getEmployeeById(Mockito.anyInt())).thenReturn(TestDataProvider.getValidEmployeeData());
        Employee employee = employeeService.createEmployee(TestDataProvider.getValidEmployeeData());
        Assert.assertEquals(employee.getId(), Integer.valueOf(2));
    }


    @Test
    public void updateEmployeeTest() throws Exception {
        Mockito.when(departmentService.isDepartmentExistsForId(Mockito.anyInt())).thenReturn(true);
        Mockito.when(employeeRepository.getEmployeeById(Mockito.anyInt())).thenReturn(TestDataProvider.getValidEmployeeData());
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(TestDataProvider.getValidEmployeeData());
        Employee employee = employeeService.updateEmployee(TestDataProvider.getValidEmployeeData());
        Assert.assertEquals(employee.getSalary(), Integer.valueOf(50000));
    }

    @Test(expected = Exception.class)
    public void updateEmployeeForInvalidEmployeeTest() throws Exception {
        Mockito.when(departmentService.isDepartmentExistsForId(Mockito.anyInt())).thenReturn(true);
        Mockito.when(employeeRepository.getEmployeeById(Mockito.anyInt())).thenReturn(null);
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(TestDataProvider.getValidEmployeeData());
        Employee employee = employeeService.updateEmployee(TestDataProvider.getValidEmployeeData());
        Assert.assertEquals(employee.getSalary(), Integer.valueOf(30000));
    }

    @Test(expected = Exception.class)
    public void updateEmployeeForInvalidDepartmentTest() throws Exception {
        Mockito.when(departmentService.isDepartmentExistsForId(Mockito.anyInt())).thenReturn(false);
        Mockito.when(employeeRepository.getEmployeeById(Mockito.anyInt())).thenReturn(null);
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(TestDataProvider.getValidEmployeeData());
        Employee employee = employeeService.updateEmployee(TestDataProvider.getValidEmployeeData());
        Assert.assertEquals(employee.getSalary(), Integer.valueOf(30000));
    }

    @Test
    public void fetchEmployeeFromDBTest() throws Exception {
        Mockito.when(employeeRepository.getEmployeeById(Mockito.anyInt())).thenReturn(TestDataProvider.getValidEmployeeData());
        Assert.assertEquals(employeeService.fetchEmployee(Mockito.anyInt()).getId(), Integer.valueOf(1));
    }

    @Test(expected = Exception.class)
    public void fetchEmployeeForInvalidIdFromDBTest() throws Exception{
        Mockito.when(employeeRepository.getEmployeeById(Mockito.anyInt())).thenReturn(null);
        Assert.assertEquals(employeeService.fetchEmployee(Mockito.anyInt()).getId(), Integer.valueOf(1));
    }

    @Test
    public void fetchAllEmployeesFromDBTest() throws Exception{
        Mockito.when(employeeRepository.findAll()).thenReturn(TestDataProvider.getAllEmployeesData());
        List<Employee> departments = employeeService.fetchAllEmployees(0,10);
        Assert.assertEquals(departments.get(0).getId(), Integer.valueOf(1));
    }

    @Test
    public void fetchAllEmployeesEmptyResponseFromDBTest() throws Exception{
        Mockito.when(employeeRepository.findAll()).thenReturn(Collections.emptyList());
        Assert.assertEquals( employeeService.fetchAllEmployees(0,10), Collections.emptyList());
    }

}
