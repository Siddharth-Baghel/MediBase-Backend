package com.medibase.service;

import com.medibase.dto.request.CreateInventoryBatchRequest;
import com.medibase.dto.request.UpdateBatchQuantityRequest;
import com.medibase.entity.Inventory;
import com.medibase.entity.InventoryBatch;
import com.medibase.repository.InventoryBatchRepository;
import com.medibase.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class InventoryBatchService {
    private final InventoryBatchRepository batchRepository;
    private final InventoryRepository inventoryRepository;
    private final CurrentUserService currentUserService;
    public InventoryBatchService(InventoryBatchRepository batchRepository, InventoryRepository inventoryRepository, CurrentUserService currentUserService){this.batchRepository=batchRepository;this.inventoryRepository=inventoryRepository;this.currentUserService=currentUserService;}

    @Transactional
    public InventoryBatch createBatch(CreateInventoryBatchRequest request){
        Inventory inventory=getOwnedInventory(request.getInventoryId());
        if(batchRepository.findByInventoryIdAndBatchNumber(inventory.getId(),request.getBatchNumber()).isPresent()) throw new RuntimeException("Batch number already exists for this inventory");
        InventoryBatch batch=new InventoryBatch(inventory,request.getBatchNumber(),request.getExpiryDate(),request.getQuantity(),request.getMrp(),request.getPurchasePrice(),request.getSellingPrice());
        inventory.setTotalQuantity(inventory.getTotalQuantity()+request.getQuantity());
        inventoryRepository.save(inventory);
        return batchRepository.save(batch);
    }
    public List<InventoryBatch> getBatchesByInventory(Long inventoryId){getOwnedInventory(inventoryId);return batchRepository.findByInventoryId(inventoryId);}
    @Transactional
    public InventoryBatch updateBatchQuantity(Long batchId, UpdateBatchQuantityRequest request){
        InventoryBatch batch=getOwnedBatch(batchId);
        int old=batch.getQuantity(); int updated=request.getQuantity();
        batch.setQuantity(updated);
        Inventory inventory=batch.getInventory(); inventory.setTotalQuantity(inventory.getTotalQuantity()-old+updated);
        inventoryRepository.save(inventory);
        return batchRepository.save(batch);
    }
    public InventoryBatch getOwnedBatch(Long id){
        InventoryBatch batch=batchRepository.findById(id).orElseThrow(()->new RuntimeException("Inventory batch not found"));
        if(!batch.getInventory().getPharmacy().getId().equals(currentUserService.getCurrentPharmacy().getId())) throw new RuntimeException("Access denied");
        return batch;
    }
    private Inventory getOwnedInventory(Long id){
        Inventory inventory=inventoryRepository.findById(id).orElseThrow(()->new RuntimeException("Inventory not found"));
        if(!inventory.getPharmacy().getId().equals(currentUserService.getCurrentPharmacy().getId())) throw new RuntimeException("Access denied");
        return inventory;
    }
}
