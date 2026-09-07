package com.medibase.repository;

import com.medibase.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByPharmacyIdAndMedicineId(
            Long pharmacyId,
            Long medicineId
    );

    List<Inventory> findByPharmacyId(Long pharmacyId);

    List<Inventory> findByMedicineId(Long medicineId);

    List<Inventory> findByTotalQuantityLessThanEqual(
            Integer quantity
    );
}