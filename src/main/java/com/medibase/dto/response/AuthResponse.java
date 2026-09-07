package com.medibase.dto.response;

public class AuthResponse {
    private String token;
    private String message;
    private Long userId;
    private String name;
    private String role;
    private Long organizationId;
    private Long pharmacyId;
    private String pharmacyName;

    public AuthResponse() {
    }

    public AuthResponse(String token, String message, Long userId, String name, String role, Long organizationId, Long pharmacyId, String pharmacyName) {
        this.token = token;
        this.message = message;
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.organizationId = organizationId;
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String v) {
        token = v;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String v) {
        message = v;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long v) {
        userId = v;
    }

    public String getName() {
        return name;
    }

    public void setName(String v) {
        name = v;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String v) {
        role = v;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long v) {
        organizationId = v;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long v) {
        pharmacyId = v;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String v) {
        pharmacyName = v;
    }
}
