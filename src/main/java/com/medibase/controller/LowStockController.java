package com.medibase.controller;

import com.medibase.entity.Inventory;
import com.medibase.service.LowStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class LowStockController {

    private final LowStockService lowStockService;

    public LowStockController(
            LowStockService lowStockService
    ) {
        this.lowStockService = lowStockService;
    }


    @GetMapping("/low-stock")
    public ResponseEntity<List<Inventory>> getLowStockItems() {

        return ResponseEntity.ok(
                lowStockService.getLowStockItems()
        );
    }
}