package com.medibase.controller;

import com.medibase.dto.request.StockAdjustmentRequest;
import com.medibase.entity.StockAdjustment;
import com.medibase.service.StockAdjustmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stock-adjustments")
public class StockAdjustmentController {

    private final StockAdjustmentService stockAdjustmentService;

    public StockAdjustmentController(
            StockAdjustmentService stockAdjustmentService
    ) {
        this.stockAdjustmentService = stockAdjustmentService;
    }


    // Create Stock Adjustment
    @PostMapping
    public ResponseEntity<StockAdjustment> adjustStock(
            @Valid @RequestBody StockAdjustmentRequest request
    ) {

        StockAdjustment adjustment =
                stockAdjustmentService.adjustStock(request);

        return ResponseEntity.ok(adjustment);
    }

    // Get stock adjustment history by Inventory
    @GetMapping("/inventory/{inventoryId}")
    public ResponseEntity<List<StockAdjustment>> getHistoryByInventory(
            @PathVariable Long inventoryId
    ) {

        return ResponseEntity.ok(
                stockAdjustmentService.getHistoryByInventory(inventoryId)
        );
    }


    // Get stock adjustment history by Batch
    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<StockAdjustment>> getHistoryByBatch(
            @PathVariable Long batchId
    ) {

        return ResponseEntity.ok(
                stockAdjustmentService.getHistoryByBatch(batchId)
        );
    }
}