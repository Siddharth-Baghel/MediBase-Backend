package com.medibase.dto.request;

import com.medibase.entity.Role;

public class RegisterRequest {

    private String name;

    private String email;

    private String password;

    private Role role;

    private Long organizationId;

    private Long pharmacyId;


    public RegisterRequest() {
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }
}