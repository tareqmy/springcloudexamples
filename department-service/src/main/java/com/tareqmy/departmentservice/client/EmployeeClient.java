package com.tareqmy.departmentservice.client;

import com.tareqmy.departmentservice.model.Employee;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "employee-service")
public interface EmployeeClient {

    @Cacheable(value = "employees_cache", key = "'id'")
    @GetMapping("/department/{departmentId}")
    List<Employee> findByDepartment(@RequestHeader("Authorization") String authorizationHeader,
                                    @PathVariable("departmentId") Long departmentId);

}
