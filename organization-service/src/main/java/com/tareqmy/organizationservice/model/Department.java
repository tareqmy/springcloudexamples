package com.tareqmy.organizationservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Department {

    private Long id;

    private String name;

    private List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        super();
        this.name = name;
    }
}
