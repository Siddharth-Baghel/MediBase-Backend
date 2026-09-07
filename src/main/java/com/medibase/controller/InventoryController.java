package com.medibase.controller;

import com.medibase.dto.request.CreateInventoryRequest;
import com.medibase.entity.Inventory;
import com.medibase.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService){this.inventoryService=inventoryService;}
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Inventory createInventory(@Valid @RequestBody CreateInventoryRequest request){return inventoryService.createInventory(request);}
    @GetMapping public List<Inventory> getCurrentInventory(){return inventoryService.getCurrentInventory();}
    @GetMapping("/pharmacy/{pharmacyId}") public List<Inventory> getInventoryByPharmacy(@PathVariable Long pharmacyId){return inventoryService.getInventoryByPharmacy(pharmacyId);}
    @GetMapping("/{inventoryId}") public Inventory getInventoryById(@PathVariable Long inventoryId){return inventoryService.getInventoryById(inventoryId);}
}
