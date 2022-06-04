package com.tareqmy.departmentservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tareqmy.departmentservice.client.EmployeeClient;
import com.tareqmy.departmentservice.model.Department;
import com.tareqmy.departmentservice.repository.DepartmentRepository;
import com.tareqmy.departmentservice.utils.ArtificialUtils;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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

    @GetMapping("/{id}")
    public Department findById(@PathVariable("id") Long id) {
        log.info("Department find: id={}", id);
        return repository.findById(id);
    }

    @HystrixCommand(defaultFallback = "findAllFallBack")
    @GetMapping("/")
    public List<Department> findAll(@AuthenticationPrincipal Jwt jwt) {
        log.info("Department find");
        log.info("{}", jwt.getClaims());
        log.info("{}", jwt.getHeaders());
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
    public List<Department> findByOrganizationWithEmployees(@Parameter(hidden = true) @RequestHeader("Authorization") String authorizationHeader,
                                                            @PathVariable("organizationId") Long organizationId) {
        log.info("Department find: organizationId={}", organizationId);
        List<Department> departments = repository.findByOrganization(organizationId);
        departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(authorizationHeader, d.getId())));
        return departments;
    }

}
