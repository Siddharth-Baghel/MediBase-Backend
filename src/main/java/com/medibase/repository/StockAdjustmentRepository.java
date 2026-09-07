package com.medibase.repository;

import com.medibase.entity.StockAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockAdjustmentRepository
        extends JpaRepository<StockAdjustment, Long> {

    List<StockAdjustment> findByInventoryId(Long inventoryId);

    List<StockAdjustment> findByInventoryBatchId(Long inventoryBatchId);
}