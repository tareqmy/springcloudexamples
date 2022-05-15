package com.tareqmy.organizationservice.repository;

import com.tareqmy.organizationservice.model.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrganizationRepository {

    private final List<Organization> organizations = new ArrayList<>();

    public Organization add(Organization organization) {
        organization.setId((long) (organizations.size() + 1));
        organizations.add(organization);
        return organization;
    }

    public Organization findById(Long id) {
        Optional<Organization> organization = organizations.stream().filter(a -> a.getId().equals(id)).findFirst();
        return organization.orElse(null);
    }

    public List<Organization> findAll() {
        return organizations;
    }

    public List<Organization> findFirst2() {
        return organizations.subList(1, 2);
    }
}
