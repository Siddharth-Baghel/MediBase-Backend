package com.medibase.service;

import com.medibase.dto.request.CreatePharmacyRequest;
import com.medibase.entity.Organization;
import com.medibase.entity.Pharmacy;
import com.medibase.repository.OrganizationRepository;
import com.medibase.repository.PharmacyRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final OrganizationRepository organizationRepository;


    public PharmacyService(
            PharmacyRepository pharmacyRepository,
            OrganizationRepository organizationRepository
    ) {
        this.pharmacyRepository = pharmacyRepository;
        this.organizationRepository = organizationRepository;
    }


    public Pharmacy createPharmacy(CreatePharmacyRequest request) {

        Organization organization = organizationRepository
                .findById(request.getOrganizationId())
                .orElseThrow(() ->
                        new RuntimeException("Organization not found"));

        Pharmacy pharmacy = new Pharmacy(
                request.getName(),
                request.getPhone(),
                request.getEmail(),
                request.getAddress(),
                request.getCity(),
                request.getLatitude(),
                request.getLongitude(),
                organization
        );

        return pharmacyRepository.save(pharmacy);
    }


    public List<Pharmacy> getAllPharmacies() {
        return pharmacyRepository.findAll();
    }


    public List<Pharmacy> getPharmaciesByOrganization(Long organizationId) {
        return pharmacyRepository.findByOrganizationId(organizationId);
    }
}