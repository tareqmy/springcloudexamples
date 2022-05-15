package com.tareqmy.employeeservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tareqmy.employeeservice.model.Employee;
import com.tareqmy.employeeservice.repository.EmployeeRepository;
import com.tareqmy.employeeservice.utils.ArtificialUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/")
    public Employee add(@RequestBody Employee employee) {
        log.info("Employee add: {}", employee);
        return repository.add(employee);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        log.info("Employee find: id={}", id);
        return repository.findById(id);
    }

    @HystrixCommand(defaultFallback = "findAllFallBack")
    @GetMapping("/")
    public List<Employee> findAll() {
        log.info("Employee find");
        ArtificialUtils.artificialSlowness();
        return repository.findAll();
    }

    public List<Employee> findAllFallBack() {
        log.info("Employee find fallback");
        return repository.findFirst2();
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        log.info("Employee find: departmentId={}", departmentId);
        return repository.findByDepartment(departmentId);
    }

    @GetMapping("/organization/{organizationId}")
    public List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId) {
        log.info("Employee find: organizationId={}", organizationId);
        return repository.findByOrganization(organizationId);
    }

}
