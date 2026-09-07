package com.medibase.repository;

import com.medibase.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByPharmacyId(Long pharmacyId);
    List<Medicine> findByPharmacyIdAndNameContainingIgnoreCase(Long pharmacyId, String name);
    Optional<Medicine> findByIdAndPharmacyId(Long id, Long pharmacyId);
    boolean existsByBarcodeAndPharmacyId(String barcode, Long pharmacyId);
}
