package com.tareqmy.employeeservice;

import com.tareqmy.employeeservice.model.Employee;
import com.tareqmy.employeeservice.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@EnableHystrix
@OpenAPIDefinition(info =
@Info(title = "Employee API", version = "${springdoc.version}", description = "Documentation Employee API v1.0"),
    servers = {
    @Server(url = "{custom_server}",
        variables = {@ServerVariable(name = "custom_server", defaultValue = "http://localhost:8080/employee")})
}
)
@SpringBootApplication
public class EmployeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }

    @Bean
    EmployeeRepository repository() {
        EmployeeRepository repository = new EmployeeRepository();
        repository.add(new Employee(1L, 1L, "John Smith", 34, "Analyst"));
        repository.add(new Employee(1L, 1L, "Darren Hamilton", 37, "Manager"));
        repository.add(new Employee(1L, 1L, "Tom Scott", 26, "Developer"));
        repository.add(new Employee(1L, 2L, "Anna London", 39, "Analyst"));
        repository.add(new Employee(1L, 2L, "Patrick Dempsey", 27, "Developer"));
        repository.add(new Employee(2L, 3L, "Kevin Price", 38, "Developer"));
        repository.add(new Employee(2L, 3L, "Ian Scott", 34, "Developer"));
        repository.add(new Employee(2L, 3L, "Andrew Campton", 30, "Manager"));
        repository.add(new Employee(2L, 4L, "Steve Franklin", 25, "Developer"));
        repository.add(new Employee(2L, 4L, "Elisabeth Smith", 30, "Developer"));
        return repository;
    }
}
