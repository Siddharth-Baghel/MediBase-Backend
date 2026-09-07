package com.medibase.repository;

import com.medibase.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository
        extends JpaRepository<Organization, Long> {

    boolean existsByGstNumber(String gstNumber);
}