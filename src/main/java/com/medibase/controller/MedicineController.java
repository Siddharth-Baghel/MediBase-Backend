package com.medibase.controller;

import com.medibase.dto.request.CreateMedicineRequest;
import com.medibase.entity.Medicine;
import com.medibase.service.MedicineService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicines")
public class MedicineController {

    private final MedicineService medicineService;


    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }


    // Create medicine
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Medicine createMedicine(
            @Valid @RequestBody CreateMedicineRequest request
    ) {
        return medicineService.createMedicine(request);
    }


    // Get all medicines
    @GetMapping
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }


    // Get medicine by ID
    @GetMapping("/{id}")
    public Medicine getMedicineById(
            @PathVariable Long id
    ) {
        return medicineService.getMedicineById(id);
    }


    // Search medicine
    @GetMapping("/search")
    public List<Medicine> searchMedicine(
            @RequestParam String name
    ) {
        return medicineService.searchMedicine(name);
    }
}