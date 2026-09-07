package com.medibase.repository;

import com.medibase.entity.InventoryBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InventoryBatchRepository
        extends JpaRepository<InventoryBatch, Long> {

    List<InventoryBatch> findByInventoryId(Long inventoryId);

    Optional<InventoryBatch> findByInventoryIdAndBatchNumber(
            Long inventoryId,
            String batchNumber
    );

    List<InventoryBatch> findByExpiryDateBefore(LocalDate date);

    List<InventoryBatch> findByExpiryDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );
}