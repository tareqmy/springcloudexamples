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
public class Organization {

    private Long id;

    private String name;

    private String address;

    private List<Department> departments = new ArrayList<>();

    private List<Employee> employees = new ArrayList<>();

    public Organization() {

    }
}
