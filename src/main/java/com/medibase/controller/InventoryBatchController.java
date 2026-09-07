package com.medibase.controller;

import com.medibase.dto.request.CreateInventoryBatchRequest;
import com.medibase.dto.request.UpdateBatchQuantityRequest;
import com.medibase.entity.InventoryBatch;
import com.medibase.service.InventoryBatchService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory-batches")
public class InventoryBatchController {

    private final InventoryBatchService inventoryBatchService;


    public InventoryBatchController(
            InventoryBatchService inventoryBatchService
    ) {
        this.inventoryBatchService = inventoryBatchService;
    }


    // Add a batch
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryBatch createBatch(
            @Valid @RequestBody CreateInventoryBatchRequest request
    ) {
        return inventoryBatchService.createBatch(request);
    }


    // Get all batches of an inventory item
    @GetMapping("/inventory/{inventoryId}")
    public List<InventoryBatch> getBatchesByInventory(
            @PathVariable Long inventoryId
    ) {
        return inventoryBatchService
                .getBatchesByInventory(inventoryId);
    }

    @PatchMapping("/{batchId}/quantity")
    public InventoryBatch updateBatchQuantity(
            @PathVariable Long batchId,
            @Valid @RequestBody UpdateBatchQuantityRequest request
    ) {
        return inventoryBatchService
                .updateBatchQuantity(batchId, request);
    }
}