package com.medibase.controller;

import com.medibase.entity.InventoryBatch;
import com.medibase.service.ExpiryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expiry")
public class ExpiryController {

    private final ExpiryService expiryService;

    public ExpiryController(ExpiryService expiryService) {
        this.expiryService = expiryService;
    }


    // Get all expired batches
    @GetMapping("/expired")
    public ResponseEntity<List<InventoryBatch>> getExpiredBatches() {

        return ResponseEntity.ok(
                expiryService.getExpiredBatches()
        );
    }


    // Get near expiry batches
    @GetMapping("/near-expiry")
    public ResponseEntity<List<InventoryBatch>> getNearExpiryBatches(

            @RequestParam(defaultValue = "30") int days
    ) {

        return ResponseEntity.ok(
                expiryService.getNearExpiryBatches(days)
        );
    }
}