package com.tareqmy.organizationservice.client;

import com.tareqmy.organizationservice.model.Department;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "department-service")
public interface DepartmentClient {

    @Cacheable(value = "departments_cache", key = "'id'")
    @GetMapping("/organization/{organizationId}")
    List<Department> findByOrganization(@RequestHeader("Authorization") String authorizationHeader,
                                        @PathVariable("organizationId") Long organizationId);

    @Cacheable(value = "departments_with_employees_cache", key = "'id'")
    @GetMapping("/organization/{organizationId}/with-employees")
    List<Department> findByOrganizationWithEmployees(@RequestHeader("Authorization") String authorizationHeader,
                                                     @PathVariable("organizationId") Long organizationId);

}
