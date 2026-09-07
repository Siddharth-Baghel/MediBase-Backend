package com.medibase.service;

import com.medibase.dto.request.CreateOrganizationRequest;
import com.medibase.entity.Organization;
import com.medibase.repository.OrganizationRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization createOrganization(CreateOrganizationRequest request) {

        if (request.getGstNumber() != null &&
                organizationRepository.existsByGstNumber(request.getGstNumber())) {

            throw new RuntimeException("GST number already exists");
        }

        Organization organization = new Organization(
                request.getName(),
                request.getOwnerName(),
                request.getGstNumber(),
                request.getDrugLicenseNumber(),
                request.getPhone(),
                request.getEmail()
        );

        return organizationRepository.save(organization);
    }
}