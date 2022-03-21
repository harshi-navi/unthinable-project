package com.employee.project;

import java.util.Collections;
import java.util.List;

import com.employee.project.repository.DepartmentRepository;
import com.employee.project.model.Department;
import com.employee.project.service.DepartmentService;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DepartmentServiceTest {
    
    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    DepartmentService departmentService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createDepartmentTest() throws Exception {
        Mockito.when(departmentRepository.save(Mockito.any())).thenReturn(TestDataProvider.getValidDepartmentData());
        Department department = departmentService.createDepartment(TestDataProvider.getValidDepartmentData());
        Assert.assertEquals(department.getName(), "FINANCE");
    }

    @Test(expected = Exception.class)
    public void createDepartmentForInvalidDataTest() throws Exception {
        Mockito.when(departmentRepository.getDepartmentById(Mockito.anyInt())).thenReturn(TestDataProvider.getValidDepartmentData());
        Department department = departmentService.createDepartment(TestDataProvider.getValidDepartmentData());
        Assert.assertEquals(department.getName(), "HR");
    }

    @Test
    public void updateDepartmentTest() throws Exception {
        Mockito.when(departmentRepository.getDepartmentById(Mockito.anyInt())).thenReturn(TestDataProvider.getValidDepartmentData());
        Mockito.when(departmentRepository.save(Mockito.any())).thenReturn(TestDataProvider.getValidDepartmentData());
        Department department = departmentService.updateDepartment(TestDataProvider.getValidDepartmentData());
        Assert.assertEquals(department.getName(), "FINANCE");
    }

    @Test(expected = Exception.class)
    public void updateDepartmentForInvalidDataTest() throws Exception {
        Mockito.when(departmentRepository.getDepartmentById(Mockito.anyInt())).thenReturn(null);
        Mockito.when(departmentRepository.save(Mockito.any())).thenReturn(TestDataProvider.getValidDepartmentData());
        Department department = departmentService.updateDepartment(TestDataProvider.getValidDepartmentData());
        Assert.assertEquals(department.getName(), "FINANCE");
    }

    @Test
    public void fetchDepartmentFromDBTest() throws Exception {
        Mockito.when(departmentRepository.getDepartmentById(Mockito.anyInt())).thenReturn(TestDataProvider.getValidDepartmentData());
        Assert.assertEquals(departmentService.fetchDepartment(Mockito.anyInt()).getName(), "FINANCE");
    }

    @Test(expected = Exception.class)
    public void fetchDepartmentForInvalidIdFromDBTest() throws Exception{
        Mockito.when(departmentRepository.getDepartmentById(Mockito.anyInt())).thenReturn(null);
        Assert.assertEquals(departmentService.fetchDepartment(Mockito.anyInt()).getName(), "FINANCE");
    }

    @Test
    public void fetchAllDepartmentsFromDBTest() throws Exception{
        Mockito.when(departmentRepository.findAll()).thenReturn(TestDataProvider.getAllDepartmentsData());
        List<Department> departments = departmentService.fetchAllDepartments(0,10);
        Assert.assertEquals(departments.get(0).getName(), "FINANCE");
    }

    @Test
    public void fetchAllDepartmentsEmptyResponseFromDBTest() throws Exception{
        Mockito.when(departmentRepository.findAll()).thenReturn(Collections.emptyList());
        Assert.assertEquals( departmentService.fetchAllDepartments(0,10), Collections.emptyList());
    }
}
