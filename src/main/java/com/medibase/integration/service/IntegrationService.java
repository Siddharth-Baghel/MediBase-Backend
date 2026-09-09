package com.medibase.integration.service;

import com.medibase.entity.Inventory;
import com.medibase.integration.dto.IntegrationMedicineResponse;
import com.medibase.repository.InventoryRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegrationService {

    private final InventoryRepository inventoryRepository;

    public IntegrationService(
            InventoryRepository inventoryRepository
    ) {
        this.inventoryRepository = inventoryRepository;
    }


    public List<IntegrationMedicineResponse> searchMedicine(
            String query
    ) {

        List<Inventory> inventories =
                inventoryRepository
                        .findByMedicine_NameContainingIgnoreCase(query);


        return inventories.stream()

                // कोई stock filter नहीं
                // Inventory में medicine मौजूद है तो result में दिखेगी

                .map(inventory -> {

                    var medicine = inventory.getMedicine();
                    var pharmacy = inventory.getPharmacy();

                    Integer quantity = inventory.getTotalQuantity() != null
                            ? inventory.getTotalQuantity()
                            : 0;

                    return new IntegrationMedicineResponse(

                            medicine.getId(),
                            medicine.getName(),
                            medicine.getManufacturer(),
                            medicine.getComposition(),
                            medicine.getStrength(),
                            medicine.getDosageForm(),

                            pharmacy.getId(),
                            pharmacy.getName(),
                            pharmacy.getPhone(),
                            pharmacy.getAddress(),
                            pharmacy.getCity(),

                            pharmacy.getLatitude(),
                            pharmacy.getLongitude(),

                            quantity
                    );
                })

                .toList();
    }
}