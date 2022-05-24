package com.tareqmy.organizationservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tareqmy.organizationservice.client.DepartmentClient;
import com.tareqmy.organizationservice.client.EmployeeClient;
import com.tareqmy.organizationservice.model.Organization;
import com.tareqmy.organizationservice.repository.OrganizationRepository;
import com.tareqmy.organizationservice.utils.ArtificialUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class OrganizationController {

	private final OrganizationRepository repository;

    private final DepartmentClient departmentClient;

    private final EmployeeClient employeeClient;

	public OrganizationController(OrganizationRepository repository, DepartmentClient departmentClient,
                                  EmployeeClient employeeClient) {
		this.repository = repository;
		this.departmentClient = departmentClient;
		this.employeeClient = employeeClient;
	}

	@PostMapping
	public Organization add(@RequestBody Organization organization) {
		log.info("Organization add: {}", organization);
		return repository.add(organization);
	}

    @HystrixCommand(defaultFallback = "findAllFallBack")
	@GetMapping
	public List<Organization> findAll() {
        log.info("Organization find");
        ArtificialUtils.artificialSlowness();
		return repository.findAll();
	}

    public List<Organization> findAllFallBack() {
        log.info("Organization find fallback");
        return repository.findFirst2();
    }

	@GetMapping("/{id}")
	public Organization findById(@PathVariable("id") Long id) {
        log.info("Organization find: id={}", id);
		return repository.findById(id);
	}

	@GetMapping("/{id}/with-departments")
	public Organization findByIdWithDepartments(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,
                                                @PathVariable("id") Long id) {
        log.info("Organization find: id={}", id);
		Organization organization = repository.findById(id);
		organization.setDepartments(departmentClient.findByOrganization(authorizationHeader, organization.getId()));
		return organization;
	}

	@GetMapping("/{id}/with-departments-and-employees")
	public Organization findByIdWithDepartmentsAndEmployees(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,
                                                            @PathVariable("id") Long id) {
        log.info("Organization find: id={}", id);
		Organization organization = repository.findById(id);
		organization.setDepartments(departmentClient.findByOrganizationWithEmployees(authorizationHeader, organization.getId()));
		return organization;
	}

	@GetMapping("/{id}/with-employees")
	public Organization findByIdWithEmployees(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,
                                              @PathVariable("id") Long id) {
        log.info("Organization find: id={}", id);
		Organization organization = repository.findById(id);
		organization.setEmployees(employeeClient.findByOrganization(authorizationHeader, organization.getId()));
		return organization;
	}

}
