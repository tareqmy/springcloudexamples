package com.tareqmy.departmentservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tareqmy.departmentservice.client.EmployeeClient;
import com.tareqmy.departmentservice.model.Department;
import com.tareqmy.departmentservice.repository.DepartmentRepository;
import com.tareqmy.departmentservice.utils.ArtificialUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DepartmentController {

    private final DepartmentRepository repository;

    private final EmployeeClient employeeClient;

    public DepartmentController(DepartmentRepository repository, EmployeeClient employeeClient) {
        this.repository = repository;
        this.employeeClient = employeeClient;
    }

    @PostMapping("/")
    public Department add(@RequestBody Department department) {
        log.info("Department add: {}", department);
        return repository.add(department);
    }

    @HystrixCommand(defaultFallback = "findAllFallBack")
    @GetMapping("/{id}")
    public Department findById(@PathVariable("id") Long id) {
        log.info("Department find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/")
    public List<Department> findAll() {
        log.info("Department find");
        ArtificialUtils.artificialSlowness();
        return repository.findAll();
    }

    public List<Department> findAllFallBack() {
        log.info("Department find fallback");
        return repository.findFirst2();
    }

    @GetMapping("/organization/{organizationId}")
    public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
        log.info("Department find: organizationId={}", organizationId);
        return repository.findByOrganization(organizationId);
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
        log.info("Department find: organizationId={}", organizationId);
        List<Department> departments = repository.findByOrganization(organizationId);
        departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
        return departments;
    }

}
