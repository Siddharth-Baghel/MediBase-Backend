package com.medibase.controller;

import com.medibase.dto.request.CreatePharmacyRequest;
import com.medibase.entity.Pharmacy;
import com.medibase.service.PharmacyService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pharmacies")
public class PharmacyController {

    private final PharmacyService pharmacyService;


    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pharmacy createPharmacy(
            @Valid @RequestBody CreatePharmacyRequest request
    ) {
        return pharmacyService.createPharmacy(request);
    }


    @GetMapping
    public List<Pharmacy> getAllPharmacies() {
        return pharmacyService.getAllPharmacies();
    }


    @GetMapping("/organization/{organizationId}")
    public List<Pharmacy> getPharmaciesByOrganization(
            @PathVariable Long organizationId
    ) {
        return pharmacyService.getPharmaciesByOrganization(organizationId);
    }
}