package com.medibase.integration;

import com.medibase.integration.dto.IntegrationMedicineResponse;
import com.medibase.integration.service.IntegrationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/integration")
public class IntegrationController {

    private final IntegrationService integrationService;


    public IntegrationController(
            IntegrationService integrationService
    ) {
        this.integrationService = integrationService;
    }


    @GetMapping("/medicines/search")
    public ResponseEntity<List<IntegrationMedicineResponse>> searchMedicine(

            @RequestParam String query

    ) {

        List<IntegrationMedicineResponse> results =
                integrationService.searchMedicine(query);

        return ResponseEntity.ok(results);
    }
}