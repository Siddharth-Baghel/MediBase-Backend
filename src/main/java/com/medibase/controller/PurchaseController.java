package com.medibase.controller;

import com.medibase.dto.request.CreatePurchaseRequest;
import com.medibase.entity.Purchase;
import com.medibase.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;


    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    // CREATE PURCHASE
    @PostMapping
    public ResponseEntity<Purchase> createPurchase(
            @Valid @RequestBody CreatePurchaseRequest request
    ) {

        Purchase purchase =
                purchaseService.createPurchase(request);

        return new ResponseEntity<>(
                purchase,
                HttpStatus.CREATED
        );
    }
}