package com.medibase.service;

import com.medibase.dto.request.StockAdjustmentRequest;
import com.medibase.entity.*;
import com.medibase.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class StockAdjustmentService {
    private final StockAdjustmentRepository adjustmentRepository;
    private final InventoryBatchRepository batchRepository;
    private final InventoryRepository inventoryRepository;
    private final CurrentUserService currentUserService;
    public StockAdjustmentService(StockAdjustmentRepository adjustmentRepository, InventoryBatchRepository batchRepository, InventoryRepository inventoryRepository, CurrentUserService currentUserService){this.adjustmentRepository=adjustmentRepository;this.batchRepository=batchRepository;this.inventoryRepository=inventoryRepository;this.currentUserService=currentUserService;}
    @Transactional public StockAdjustment adjustStock(StockAdjustmentRequest request){
        InventoryBatch batch=getOwnedBatch(request.getBatchId());Inventory inventory=batch.getInventory();int nq=batch.getQuantity()+request.getQuantityChange();int nt=inventory.getTotalQuantity()+request.getQuantityChange();
        if(nq<0||nt<0)throw new RuntimeException("Insufficient stock. Adjustment would make stock negative.");
        batch.setQuantity(nq);inventory.setTotalQuantity(nt);batchRepository.save(batch);inventoryRepository.save(inventory);
        return adjustmentRepository.save(new StockAdjustment(inventory,batch,request.getQuantityChange(),request.getReason(),request.getNotes()));
    }
    public List<StockAdjustment> getHistoryByInventory(Long id){Inventory i=inventoryRepository.findById(id).orElseThrow(()->new RuntimeException("Inventory not found"));check(i);return adjustmentRepository.findByInventoryId(id);}
    public List<StockAdjustment> getHistoryByBatch(Long id){InventoryBatch b=getOwnedBatch(id);return adjustmentRepository.findByInventoryBatchId(b.getId());}
    private InventoryBatch getOwnedBatch(Long id){InventoryBatch b=batchRepository.findById(id).orElseThrow(()->new RuntimeException("Inventory batch not found"));check(b.getInventory());return b;}
    private void check(Inventory i){if(!i.getPharmacy().getId().equals(currentUserService.getCurrentPharmacy().getId()))throw new RuntimeException("Access denied");}
}
