package com.medibase.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String ownerName;

    @Column(unique = true)
    private String gstNumber;

    private String drugLicenseNumber;

    @Column(nullable = false)
    private String phone;

    private String email;


    // Empty constructor required by JPA
    public Organization() {
    }


    // Constructor used while creating organization
    public Organization(
            String name,
            String ownerName,
            String gstNumber,
            String drugLicenseNumber,
            String phone,
            String email
    ) {
        this.name = name;
        this.ownerName = ownerName;
        this.gstNumber = gstNumber;
        this.drugLicenseNumber = drugLicenseNumber;
        this.phone = phone;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public String getDrugLicenseNumber() {
        return drugLicenseNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}