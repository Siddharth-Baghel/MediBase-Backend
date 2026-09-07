package com.medibase.service;

import com.medibase.entity.Inventory;
import com.medibase.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LowStockService {
    private final InventoryRepository inventoryRepository;
    private final CurrentUserService currentUserService;
    public LowStockService(InventoryRepository inventoryRepository, CurrentUserService currentUserService){this.inventoryRepository=inventoryRepository;this.currentUserService=currentUserService;}
    public List<Inventory> getLowStockItems(){
        return inventoryRepository.findByPharmacyId(currentUserService.getCurrentPharmacy().getId()).stream()
                .filter(i->i.getTotalQuantity()<=i.getMinimumStockLevel()).toList();
    }
}
